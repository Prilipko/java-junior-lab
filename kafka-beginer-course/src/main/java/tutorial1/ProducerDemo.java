/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package tutorial1;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.11
 */
@Slf4j
public class ProducerDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();

        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>("firrst_topic", "hello world");

        producer.send(record);
        producer.flush();
        producer.close();
    }
}
