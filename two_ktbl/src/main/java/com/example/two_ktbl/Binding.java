/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.two_ktbl;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.11
 */
public interface Binding {
    String TEACHER_OUT = "tout";
    String STUDENT_OUT = "sout";

    String TEACHER_JOINED_OUT = "txout";

    String TEACHER_IN = "tin";
    String STUDENT_IN = "sin";

    @Output(TEACHER_OUT)
    MessageChannel teacherOut();

    @Output(STUDENT_OUT)
    MessageChannel studentOut();

    @Input(TEACHER_IN)
    KTable<String, Teacher> teacherIn();

    @Input(STUDENT_IN)
    KTable<String, Student> studentIn();

    @Output(TEACHER_JOINED_OUT)
    KStream<String, Teacher> teacherJoinedOut();

}
