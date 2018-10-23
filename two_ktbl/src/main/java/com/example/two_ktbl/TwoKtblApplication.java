package com.example.two_ktbl;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binder.kafka.streams.GenericKeyValueSerdeResolver;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsBinderConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(Binding.class)
@Slf4j
public class TwoKtblApplication {

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
        SpringApplication.run(TwoKtblApplication.class, args);
    }
}
