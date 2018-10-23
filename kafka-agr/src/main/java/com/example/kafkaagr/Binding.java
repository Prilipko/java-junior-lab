package com.example.kafkaagr;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface Binding {
    public final String GROUPS_SOURCE = "groups-source";
    public final String STUDENTS_SOURCE = "students-source";

    public final String GROUPS_SINK = "groups-sink";
    public final String STUDENTS_SINK = "students-sink";


    public final String EXTENDED_STUDENTS_STORE = "extended-students-sink";
    public final String EXTENDED_GROUPS_STORE = "extended-groups-sink";

    public final String STUDENTS_STORE = "students-store";
    public final String GROUPS_STORE = "groups-store";

    public final String STUDENTS_STORE_SINK = "students-store-sink";
    public final String GROUPS_STORE_SINK = "groups-store-sink";

    public final String CONNECTIONS_TOPIC = "connections";

    @Input(GROUPS_SOURCE)
    KStream<String, Group> groups();

    @Input(STUDENTS_SOURCE)
    KStream<String, Student> students();

    @Input(GROUPS_STORE_SINK)
    KStream<String, Group> groupsStore();

//    @Output(STUDENTS_SINK)
//    KTable<Long, ExtendedStudent> extendedStudents();
//
//    @Input(GROUPS_SINK)
//    KTable<Long, ExtendedGroup> extendedGroup();
}
