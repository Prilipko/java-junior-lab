package com.example.kfkstrm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.GenericKeyValueSerdeResolver;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsBinderConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBinding(KfkBinding.class)
@Slf4j
public class KfkstrmApplication {

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

//    @Bean
//    public GlobalKTable<Long, Student> globalKTable() {
//        final JsonSerde<Student> studentJsonSerde = new JsonSerde<>(Student.class);
//
//        final StreamsBuilder builder = new StreamsBuilder();
//        return builder.globalTable("sin", Materialized.<Long, Student, KeyValueStore<Bytes, byte[]>>as("sstore")
//                .withKeySerde(Serdes.Long())
//                .withValueSerde(studentJsonSerde));
////        return new SerdeResolverBeanFactoryPostProcessor();
//    }

    @Component
    public static class VisitEventSource implements ApplicationRunner {

        private final MessageChannel visitOut;
        private final MessageChannel studentOut;
        private final MessageChannel roomOut;
//        private final MessageChannel visitExOut;

        public VisitEventSource(KfkBinding binding) {

            visitOut = binding.visitOut();
            studentOut = binding.studentOut();
            roomOut = binding.roomOut();
//            visitExOut = binding.visitExOut();
        }

        @Override
        public void run(final ApplicationArguments args) throws Exception {
            List<Long> visitIds = new ArrayList<>();
            visitIds.add(null);
            List<Student> students = Arrays.asList(
                    new Student(0L, "Stud#1"),
                    new Student(1L, "Stud#2"),
                    new Student(2L, "Stud#3"),
                    new Student(3L, "Stud#4"),
                    new Student(4L, "Stud#5"));

            List<Room> rooms = Arrays.asList(
                    new Room(0L, "Room#1"),
                    new Room(1L, "Room#2"),
                    new Room(2L, "Room#3"),
                    new Room(3L, "Room#4"),
                    new Room(4L, "Room#5"));

            students.stream()
                    .map(student -> MessageBuilder.withPayload(student)
                                                  .setHeader(KafkaHeaders.MESSAGE_KEY, student.getId())
                                                  .build())
                    .forEach(studentOut::send);

            rooms.stream()
                 .map(room -> MessageBuilder.withPayload(room)
                                            .setHeader(KafkaHeaders.MESSAGE_KEY, room.getId())
                                            .build())
                 .forEach(roomOut::send);

            final Random random = new Random();
            Runnable runnable = () -> {
                VisitEvent visitEvent = new VisitEvent(
                        random.nextLong(),
                        (long) random.nextInt(5),
                        (long) random.nextInt(5),
                        random.nextLong(),
                        random.nextBoolean()
                                ? visitIds.get(random.nextInt(visitIds.size()))
                                : null);
                try {

                    Message<VisitEvent> message = MessageBuilder.withPayload(visitEvent)
                                                                .setHeader(KafkaHeaders.MESSAGE_KEY,
                                                                           visitEvent.getId())
                                                                .build();
                    visitOut.send(message);
                    visitIds.add(visitEvent.getId());
//                    log.info("sent {}", message);
                } catch (Exception e) {
                    log.error("visitOut.send", e);
                }
            };
            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(KfkstrmApplication.class, args);
    }
}


interface KfkBinding {

    String VISIT_OUT = "vout";
    String VISIT_EX_OUT = "vxout";
    String STUDENT_OUT = "sout";
    String ROOM_OUT = "rout";

    String VISIT_IN = "vin";
    String VISIT_IN_PROC = "vinproc";
    String VISIT_EX_IN = "vxin";
    String STUDENT_IN = "sin";
    String ROOM_IN = "rin";

    @Output(VISIT_OUT)
    MessageChannel visitOut();

    @Output(VISIT_EX_OUT)
    KStream<Long, VisitEventEx> visitExOut();

    @Output(STUDENT_OUT)
    MessageChannel studentOut();

    @Output(ROOM_OUT)
    MessageChannel roomOut();

    @Input(VISIT_IN)
    KStream<Long, VisitEvent> visitIn();

    @Input(VISIT_IN_PROC)
    KStream<Long, VisitEvent> visitInProc();

    @Input(VISIT_EX_IN)
    KStream<Long, VisitEventEx> visitExIn();

    @Input(STUDENT_IN)
    KTable<Long, Student> studentIn();

    @Input(ROOM_IN)
    KTable<Long, Room> roomIn();
}

