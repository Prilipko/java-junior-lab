/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package org.springframework.cloud.stream.binder.kafka.streams;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Utils;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsConsumerProperties;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsProducerProperties;
import org.springframework.kafka.support.serializer.JsonSerde;

/**
 * KeyValueSerdeResolver with generic support for {@link JsonSerde}
 *
 * @author andrii.lukhanin (alukhani)
 * @since 0.12
 */
public class GenericKeyValueSerdeResolver extends KeyValueSerdeResolver {

    private final Map<String, Object> streamConfigGlobalProperties;
    private final KafkaStreamsBinderConfigurationProperties binderConfigurationProperties;

    public GenericKeyValueSerdeResolver(final Map<String, Object> streamConfigGlobalProperties,
                                        final KafkaStreamsBinderConfigurationProperties binderConfigurationProperties) {
        super(streamConfigGlobalProperties, binderConfigurationProperties);

        this.streamConfigGlobalProperties = streamConfigGlobalProperties;
        this.binderConfigurationProperties = binderConfigurationProperties;
    }

    @Override
    public Serde<?> getInboundKeySerde(final KafkaStreamsConsumerProperties extendedConsumerProperties) {
        String keySerdeString = extendedConsumerProperties.getKeySerde();

        return getKeySerde(keySerdeString);
    }

    /**
     * Copy-pasted method in order to override #getValueSerde(valueSerdeString) method
     */
    @Override
    public Serde<?> getOutboundValueSerde(final ProducerProperties producerProperties,
                                          final KafkaStreamsProducerProperties kafkaStreamsProducerProperties) {
        try {
            Serde valueSerde;
            if (producerProperties.isUseNativeEncoding()) {
                valueSerde = getValueSerde(kafkaStreamsProducerProperties.getValueSerde());
            } else {
                valueSerde = Serdes.ByteArray();
            }

            valueSerde.configure(streamConfigGlobalProperties, false);
            return valueSerde;
        } catch (ClassNotFoundException var5) {
            throw new IllegalStateException("Serde class not found: ", var5);
        }
    }

    /**
     * Copy-pasted method in order to override #getValueSerde(valueSerdeString) method
     */
    @Override
    public Serde<?> getInboundValueSerde(final ConsumerProperties consumerProperties,
                                         final KafkaStreamsConsumerProperties extendedConsumerProperties) {
        String valueSerdeString = extendedConsumerProperties.getValueSerde();

        try {
            Serde valueSerde;
            if (consumerProperties != null && consumerProperties.isUseNativeDecoding()) {
                valueSerde = getValueSerde(valueSerdeString);
            } else {
                valueSerde = Serdes.ByteArray();
            }

            valueSerde.configure(streamConfigGlobalProperties, false);
            return valueSerde;
        } catch (ClassNotFoundException var6) {
            throw new IllegalStateException("Serde class not found: ", var6);
        }
    }

    @Override
    public Serde<?> getOuboundKeySerde(final KafkaStreamsProducerProperties properties) {
        return getKeySerde(properties.getKeySerde());
    }

    /**
     * Provides Value Serde with generic class support
     *
     * @param valueSerdeString class name with generic
     * @return Serde instance
     */
    private Serde<?> getValueSerde(String valueSerdeString) throws ClassNotFoundException {
        Serde valueSerde;
        if (StringUtils.isNotBlank(valueSerdeString)) {
            final String genericClassName = getGenericClassName(valueSerdeString);

            if (StringUtils.isNotBlank(genericClassName)) {
                final String className = getClassNameWithoutGeneric(valueSerdeString);
                valueSerde = Utils.newParameterizedInstance(className, Class.class, Class.forName(genericClassName));
            } else {
                valueSerde = Utils.newInstance(valueSerdeString, Serde.class);
            }
        } else {
            valueSerde = binderConfigurationProperties.getConfiguration().containsKey("default.value.serde")
                    ? Utils.newInstance(binderConfigurationProperties.getConfiguration().get("default.value.serde"),
                                        Serde.class)
                    : Serdes.ByteArray();
        }

        return valueSerde;
    }

    private Serde<?> getKeySerde(String keySerdeString) {
        Serde<?> keySerde;
        try {
            if (org.springframework.util.StringUtils.hasText(keySerdeString)) {
                final String genericClassName = getGenericClassName(keySerdeString);

                if (StringUtils.isNotBlank(genericClassName)) {
                    final String className = getClassNameWithoutGeneric(keySerdeString);
                    keySerde = Utils.newParameterizedInstance(className, Class.class, Class.forName(genericClassName));
                } else {
                    keySerde = Utils.newInstance(keySerdeString, Serde.class);
                }
            }
            else {
                keySerde = this.binderConfigurationProperties.getConfiguration().containsKey("default.key.serde") ?
                        Utils.newInstance(this.binderConfigurationProperties.getConfiguration().get("default.key.serde"), Serde.class) : Serdes.ByteArray();
            }
            keySerde.configure(streamConfigGlobalProperties, true);

        }
        catch (ClassNotFoundException e) {
            throw new IllegalStateException("Serde class not found: ", e);
        }
        return keySerde;
    }

    private String getGenericClassName(final String valueSerdeString) {
        return StringUtils.substringBetween(valueSerdeString, "<", ">");
    }

    private String getClassNameWithoutGeneric(final String valueSerdeString) {
        return StringUtils.substringBefore(valueSerdeString, "<");
    }
}
