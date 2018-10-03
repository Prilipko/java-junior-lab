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
public class ProducerDemoWithKey {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();

        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("firrst_topic", "id_" + i, "hello world " + i);

            producer.send(record, (recordMetadata, e) -> {
                if (e != null) {
                    log.error("message did not sent {}", e.getMessage());
                    return;
                }
                log.info("message was successfully sent");
                log.info("topic: {}", recordMetadata.topic());
                log.info("partition: {}", recordMetadata.partition());
                log.info("offset: {}", recordMetadata.offset());
                log.info("timestamp: {}", recordMetadata.timestamp());
            });
        }
        producer.flush();
        producer.close();
    }
}
