/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.two_ktbl;

import java.util.Arrays;
import java.util.List;

import lombok.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.11
 */
@Component
public class EventSource implements ApplicationRunner {

    @NonNull
    private final MessageChannel teacherOut;
    @NonNull
    private final MessageChannel studentOut;

    public EventSource(final Binding binding) {
        this.teacherOut = binding.teacherOut();
        this.studentOut = binding.studentOut();
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        List<Student> students = Arrays.asList(
                Student.builder().name("Stud#1").age(21).teacher("Teacher#1").build(),
                Student.builder().name("Stud#2").age(22).teacher("Teacher#2").build(),
                Student.builder().name("Stud#3").age(23).teacher("Teacher#3").build(),
                Student.builder().name("Stud#4").age(24).teacher("Teacher#4").build(),
                Student.builder().name("Stud#5").age(25).teacher("Teacher#5").build());

        List<Teacher> teachers = Arrays.asList(
                Teacher.builder().name("Teacher#1").age(31).build(),
                Teacher.builder().name("Teacher#2").age(32).build(),
                Teacher.builder().name("Teacher#3").age(33).build(),
                Teacher.builder().name("Teacher#4").age(34).build(),
                Teacher.builder().name("Teacher#5").age(35).build());

//        students.stream()
//                .map(student -> MessageBuilder.withPayload(student)
//                                              .setHeader(KafkaHeaders.MESSAGE_KEY, student.getName())
//                                              .build())
//                .forEach(studentOut::send);
//
//        teachers.stream()
//                .map(teacher -> MessageBuilder.withPayload(teacher)
//                                              .setHeader(KafkaHeaders.MESSAGE_KEY, teacher.getName())
//                                              .build())
//                .forEach(teacherOut::send);
    }
}
