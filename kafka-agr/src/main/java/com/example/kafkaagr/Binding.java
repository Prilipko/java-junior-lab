package com.example.kafkaagr;

import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface Binding {
    public final String GROUPS_SOURCE = "group-source";
    public final String STUDENTS_SOURCE = "students-source";

    public final String GROUPS_SINK = "group-sink";
    public final String STUDENTS_SINK = "students-sink";

    @Input(GROUPS_SOURCE)
    KTable<String, Group> groups();

    @Input(STUDENTS_SOURCE)
    KTable<String, Student> students();

    @Output(STUDENTS_SINK)
    KTable<Long, ExtendedStudent> extendedStudents();

    @Input(GROUPS_SINK)
    KTable<Long, ExtendedGroup> extendedGroup();
}
