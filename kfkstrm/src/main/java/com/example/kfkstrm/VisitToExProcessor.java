
package com.example.kfkstrm;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.kafka.support.serializer.JsonSerde;


//@Component
@Slf4j
public class VisitToExProcessor {

//    @StreamListener
//    @SendTo(KfkBinding.VISIT_EX_OUT)
    public KStream<Long, VisitEventEx> process(
            @Input(KfkBinding.VISIT_IN_PROC) KStream<Long, VisitEvent> visitStream,
            @Input(KfkBinding.STUDENT_IN) KTable<Long, Student> students,
            @Input(KfkBinding.ROOM_IN) KTable<Long, Room> rooms,
            @Input(KfkBinding.VXT_IN) KTable<Long, VisitEventEx> visits
                                              ) {
        final JsonSerde<VisitEvent> visitJsonSerde = new JsonSerde<>(VisitEvent.class);
        final JsonSerde<VisitEventEx> visitExJsonSerde = new JsonSerde<>(VisitEventEx.class);

        return visitStream.map((key, visit) -> KeyValue.pair(visit.getAdditionalVisitId(), visit))
                          .through("v-proc-add-visit-repartition", Produced.with(Serdes.Long(),
                                                                                 visitJsonSerde))
                          .leftJoin(visits,
                                    (visit, visitEventEx) -> {
                                        log.info("convert: {}", visit);
                                        if (visitEventEx != null) {
                                            visitEventEx.additionalVisit = null;
                                        }
                                        return new VisitEventEx(visit.getId(),
                                                                visit.studentId,
                                                                null,
                                                                visit.roomId,
                                                                null,
                                                                visit.duration,
                                                                visit.additionalVisitId,
                                                                visitEventEx);
                                    })
                          .map((key, visit) -> KeyValue.pair(visit.getStudentId(), visit))
                          .through("v-proc-student-repartition", Produced.with(Serdes.Long(),
                                                                               visitExJsonSerde))
                          .leftJoin(students,
                                    (visit, student) -> {
                                        log.info("convert: {}", visit);
                                        return new VisitEventEx(visit.getId(),
                                                                visit.studentId,
                                                                student,
                                                                visit.roomId,
                                                                visit.room,
                                                                visit.duration,
                                                                visit.additionalVisitId,
                                                                visit.additionalVisit);
                                    })
                          .map((key, visit) -> KeyValue.pair(visit.getRoomId(), visit))
                          .through("v-proc-room-repartition", Produced.with(Serdes.Long(),
                                                                            visitExJsonSerde))
                          .leftJoin(rooms,
                                    (visit, room) -> {
                                        log.info("convert: {}", visit);
                                        return new VisitEventEx(visit.getId(),
                                                                visit.studentId,
                                                                visit.student,
                                                                visit.roomId,
                                                                room,
                                                                visit.duration,
                                                                visit.additionalVisitId,
                                                                visit.additionalVisit);
                                    })
                          .map((key, visit) -> KeyValue.pair(visit.getId(), visit));
    }
}
