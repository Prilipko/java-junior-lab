package com.example.kafkaagr;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.kafka.streams.GenericKeyValueSerdeResolver;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsBinderConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class KafkaAgrApplication {

    @Bean
    public GenericKeyValueSerdeResolver genericKeyValueSerdeResolver(
            final @Qualifier("streamConfigGlobalProperties") Map<String, Object> streamConfigGlobalProperties,
            final KafkaStreamsBinderConfigurationProperties kafkaStreamsBinderConfigurationProperties) {

        return new GenericKeyValueSerdeResolver(streamConfigGlobalProperties,
                kafkaStreamsBinderConfigurationProperties);
    }

    @Bean
    public SerdeResolverBeanFactoryPostProcessor serdeResolverBeanFactoryPostProcessor() {
        return new SerdeResolverBeanFactoryPostProcessor();
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaAgrApplication.class, args);
    }
}
