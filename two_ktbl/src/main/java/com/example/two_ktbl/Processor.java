/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.two_ktbl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
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
public class Processor {

    private final Serde<String> keySerde;
    private final JsonSerde<Teacher> teacherSerde;
    private final JsonSerde<Student> studentSerde;

    public Processor() {
        this.keySerde = Serdes.String();
        this.teacherSerde = new JsonSerde<>(Teacher.class);
        this.studentSerde = new JsonSerde<>(Student.class);
    }


    @StreamListener
    @SendTo(Binding.TEACHER_JOINED_OUT)
    public KStream<String, Teacher> processMoveStream(
            @Input(Binding.TEACHER_IN) final KTable<String, Teacher> teachers,
            @Input(Binding.STUDENT_IN) final KTable<String, Student> students) {
        return teachers.leftJoin(mapStudentsByTeacherName(students),
                                 (teacher, aggregator) -> {
                                     if (aggregator != null) {
                                         teacher.setStudents(aggregator.getStudents());
                                         log.info("teacher joined: {}", teacher);
                                     } else {
                                         log.info("teacher skipped: {}", teacher);
                                     }
                                     return teacher;
                                 }, Materialized.with(keySerde, teacherSerde))
                       .toStream();
    }

    private KTable<String, Teacher> mapStudentsByTeacherName(final KTable<String, Student> students) {

        return students.filter((key, value) -> StringUtils.isNotBlank(value.getTeacher()))
                       .groupBy(this::associateSegmentWithMoveId, Serialized.with(keySerde, studentSerde))
                       .aggregate(Teacher::new,
                                  (key, student, aggregate) -> {
                                      aggregate.getStudents().add(student);
                                      log.info("student: {} aggregated to {}", student, aggregate);
                                      return aggregate;
                                  },
                                  (key, student, aggregate) -> {
                                      aggregate.getStudents().remove(student);
                                      log.info("student: {} disaggregated to {}", student, aggregate);
                                      return aggregate;
                                  },
                                  Materialized.with(keySerde, teacherSerde));
    }

    private KeyValue<String, Student> associateSegmentWithMoveId(final String key, final Student student) {
        final String newKey = student.getTeacher();
        return new KeyValue<>(newKey, student);
    }

}
