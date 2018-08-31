package com.example.kfkstrm;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KfkStreams {

//    @Bean
//    public GlobalKTable<Long, Student> studentGlobalKTable(StreamsBuilder streamsBuilder) {
//        final JsonSerde<Student> studentJsonSerde = new JsonSerde<>(Student.class);
//        return streamsBuilder.globalTable("s",
//                                          Materialized.<Long, Student,
//                                                  KeyValueStore<Bytes, byte[]>>as(
//                                                  "s-store")
//                                                  .withKeySerde(Serdes.Long())
//                                                  .withValueSerde(studentJsonSerde)
//                                         );
//    }

    @Bean
    public KTable<Long, Student> studentKTable(StreamsBuilder streamsBuilder) {
        final JsonSerde<Student> studentJsonSerde = new JsonSerde<>(Student.class);
        return streamsBuilder.table("s",
                                    Consumed.with(Serdes.Long(), studentJsonSerde),
                                    Materialized.with(Serdes.Long(), studentJsonSerde));
    }

    @Bean
    public GlobalKTable<Long, Room> roomGlobalKTable(StreamsBuilder streamsBuilder) {
        final JsonSerde<Room> studentJsonSerde = new JsonSerde<>(Room.class);
        return streamsBuilder.globalTable("r",
                                          Materialized.<Long, Room,
                                                  KeyValueStore<Bytes, byte[]>>as(
                                                  "r-store")
                                                  .withKeySerde(Serdes.Long())
                                                  .withValueSerde(studentJsonSerde)
                                         );
    }

    @Bean
    public GlobalKTable<Long, VisitEventEx> visitWithoutAdditionalGlobalKTable(StreamsBuilder streamsBuilder) {
        final JsonSerde<VisitEventEx> visitexJsonSerde = new JsonSerde<>(VisitEventEx.class);
        return streamsBuilder.globalTable("vx-without-add",
                                          Materialized.<Long, VisitEventEx,
                                                  KeyValueStore<Bytes, byte[]>>as(
                                                  "vx-without-add-store")
                                                  .withKeySerde(Serdes.Long())
                                                  .withValueSerde(visitexJsonSerde)
                                         );
    }

//    @Bean
//    public KStream<Long, VisitEventEx> visitWithStudentsStream(StreamsBuilder streamsBuilder,
//                                                               @Qualifier("studentGlobalKTable") GlobalKTable<Long,
//                                                                       Student> studentGlobalKTable,
//                                                               @Qualifier("roomGlobalKTable") GlobalKTable<Long,
//                                                                       Room> roomGlobalKTable,
//                                                               @Qualifier("visitWithoutAdditionalGlobalKTable")
// GlobalKTable<Long,
//                                                                       VisitEventEx>
// visitWithoutAdditionalGlobalKTable
//                                                              ) {
//        final JsonSerde<VisitEvent> visitJsonSerde = new JsonSerde<>(VisitEvent.class);
//        final JsonSerde<VisitEventEx> visitexJsonSerde = new JsonSerde<>(VisitEventEx.class);
//
//        KStream<Long, VisitEvent> stream = streamsBuilder.stream("v", Consumed.with(Serdes.Long(), visitJsonSerde));
//        KStream<Long, VisitEventEx> resultStream =
//                stream
////                        .peek((key, value) -> log.info("GKT1: {}", value))
//.leftJoin(studentGlobalKTable,
//                                (key, value) -> value.getStudentId(),
//                                (visit, student) -> new VisitEventEx(visit.id,
//                                                                     visit.studentId,
//                                                                     student,
//                                                                     visit.roomId,
//                                                                     null,
//                                                                     visit.duration,
//                                                                     visit.additionalVisitId,
//                                                                     null))
////                      .peek((key, value) -> log.info("GKT2: {}", value))
//.leftJoin(roomGlobalKTable, (key, value) -> value.getRoomId(),
//                                (visit, room) -> new VisitEventEx(visit.id,
//                                                                  visit.studentId,
//                                                                  visit.student,
//                                                                  visit.roomId,
//                                                                  room,
//                                                                  visit.duration,
//                                                                  visit.additionalVisitId,
//                                                                  null))
////                      .peek((key, value) -> log.info("GKT3: {}", value))
//                ;
//        resultStream.to("vx-without-add", Produced.with(Serdes.Long(), visitexJsonSerde));
//
//        return resultStream.leftJoin(visitWithoutAdditionalGlobalKTable, (key, value) -> value.getAdditionalVisitId(),
//                                     (visit, addVisit) -> new VisitEventEx(visit.id,
//                                                                           visit.studentId,
//                                                                           visit.student,
//                                                                           visit.roomId,
//                                                                           visit.room,
//                                                                           visit.duration,
//                                                                           visit.additionalVisitId,
//                                                                           addVisit))
////                           .peek((key, value) -> log.info("GKT4: {}", value))
//                ;
//    }


    @Bean
    public KStream<Long, StudentGroupQ> studentGroupStream(StreamsBuilder streamsBuilder,
                                                           @Qualifier("studentKTable") KTable<Long,
                                                                   Student> studentKTable
                                                          ) {
        final JsonSerde<StudentGroupQ> studentGroupQSerde = new JsonSerde<>(StudentGroupQ.class);
        final JsonSerde<StudentGroup> studentGroupSerde = new JsonSerde<>(StudentGroup.class);
        final JsonSerde<Room> studentJsonSerde = new JsonSerde<>(Room.class);


//        studentKTable.outerJoin()

        KStream<Long, StudentGroupQ> stream =
                streamsBuilder.stream("sg", Consumed.with(Serdes.Long(), studentGroupQSerde));

        stream.peek((key, value) -> log.info("GROUP 1: {}", value))
              .groupByKey(Serialized.with(Serdes.Long(), studentGroupQSerde))
              .reduce((value1, value2) -> value2, Materialized.with(Serdes.Long(), studentGroupQSerde))
              .outerJoin(studentKTable, (group, student) -> {
                  log.info("GROUP 2: {}, {}", group, student);
                  return group;
              }, Materialized.with(Serdes.Long(), studentGroupQSerde));

        return stream;
    }

}
