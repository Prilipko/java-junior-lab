/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.kfkstrm;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.11
 */
@Component
@Slf4j
public class VisitToExProcessor {

//        final GlobalKTable<Long, Student> students;
//
//        @Autowired
//        public VisitToExProcessor(final GlobalKTable<Long, Student> studentGlobalKTable) {
//            this.students = studentGlobalKTable;
//        }


//            return visits.leftJoin(students, (visit, student) -> {
//                                       if (!visit.studentId.equals(student.id)) {
//                                           return null;
//                                       }
//                                       return new VisitEventEx(visit.id, student, new Room(), visit.duration,
//                                                           new VisitEventEx());
//                                   });

//            return events.leftJoin(students, (key, value) -> value.studentId,
//                                   (visit, student) -> new VisitEventEx(visit.id, student, new Room(), visit.duration,
//                                                                        new VisitEventEx()));

    @StreamListener(KfkBinding.VISIT_IN)
    @SendTo(KfkBinding.VISIT_EX_OUT)
    public KStream<Long, VisitEventEx> process(
            @Input(KfkBinding.VISIT_IN) KStream<Long, VisitEvent> visits
                                              ) {
        return visits.map((key, value) -> {
            log.info("convert: {}", value);
            VisitEventEx visitEventEx =
                    new VisitEventEx(value.id, new Student(), new Room(), value.duration, new VisitEventEx());
            return new KeyValue<>(value.id, visitEventEx);
        });
    }
}
