package com.example.kfkstrm;

import static org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.kafka.streams.GenericKeyValueSerdeResolver;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsBinderConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBinding(KfkBinding.class)
@Slf4j
@EnableKafka
@EnableKafkaStreams
public class KfkstrmApplication {

    @Bean(name = DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsConfig kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testStreams");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new StreamsConfig(props);
    }

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

    @Component
    public static class VisitEventSource implements ApplicationRunner {

        private final MessageChannel visitOut;
        private final MessageChannel studentOut;
        private final MessageChannel roomOut;
        private final MessageChannel studentGroupOut;

        public VisitEventSource(KfkBinding binding) {

            visitOut = binding.visitOut();
            studentOut = binding.studentOut();
            roomOut = binding.roomOut();
            studentGroupOut = binding.studentGroupOut();
        }

        @Override
        public void run(final ApplicationArguments args) throws Exception {
            List<Long> visitIds = new ArrayList<>();
            visitIds.add(null);
            List<Student> students = Arrays.asList(
                    new Student(0L, "Stud#1", 18, 170),
                    new Student(1L, "Stud#2", 19, 171),
                    new Student(2L, "Stud#3", 20, 172),
                    new Student(3L, "Stud#4", 21, 173),
                    new Student(4L, "Stud#5", 22, 174));

            List<StudentGroupQ> studentGroups = Arrays.asList(
                    new StudentGroupQ(0L, 18, 20, 170, 173),
                    new StudentGroupQ(1L, 18, 20, 173, 175),
                    new StudentGroupQ(2L, 20, 23, 170, 173),
                    new StudentGroupQ(3L, 20, 23, 173, 175));

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

            studentGroups.stream()
                         .map(groupQ -> MessageBuilder.withPayload(groupQ)
                                                      .setHeader(KafkaHeaders.MESSAGE_KEY, groupQ.getId())
                                                      .build())
                         .forEach(studentGroupOut::send);

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
    String STUDENT_GROUP_OUT = "sgout";

    String VISIT_IN = "vin";
    String VISIT_IN_PROC = "vinproc";
    String VISIT_EX_IN = "vxin";
    String STUDENT_IN = "sin";
    String ROOM_IN = "rin";

    String VSR = "vsr";
    String VRR = "vrr";
    String VVR = "vvr";

    String VXT_IN = "vxtin";

    @Output(VISIT_OUT)
    MessageChannel visitOut();

//    @Output(VISIT_EX_OUT)
//    KStream<Long, VisitEventEx> visitExOut();

    @Output(STUDENT_OUT)
    MessageChannel studentOut();

    @Output(STUDENT_GROUP_OUT)
    MessageChannel studentGroupOut();

    @Output(ROOM_OUT)
    MessageChannel roomOut();

//    @Input(VISIT_IN)
//    KStream<Long, VisitEvent> visitIn();
//
//    @Input(VISIT_IN_PROC)
//    KStream<Long, VisitEvent> visitInProc();
//
//    @Input(VISIT_EX_IN)
//    KStream<Long, VisitEventEx> visitExIn();
//
//    @Input(STUDENT_IN)
//    KTable<Long, Student> studentIn();
//
//    @Input(ROOM_IN)
//    KTable<Long, Room> roomIn();
//
//    @Input(VSR)
//    KTable<Long, VisitEventEx> vsr();
//
//    @Input(VRR)
//    KTable<Long, VisitEventEx> vrr();
//
//    @Input(VVR)
//    KTable<Long, VisitEventEx> vvr();
//
//    @Input(VXT_IN)
//    KTable<Long, VisitEventEx> vxtin();
}

