package com.example.kafkaagr;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StudentProcessor {

    private final MyService myService;

    private final JsonSerde<StuGro> stuGroSerde;
    private final JsonSerde<ExtendedStudent> extendedStudentSerde;

    public StudentProcessor(final MyService myService) {
        this.myService = myService;
        stuGroSerde = new JsonSerde<>(StuGro.class);
        extendedStudentSerde = new JsonSerde<>(ExtendedStudent.class);
    }

    @StreamListener
    public void process(@Input(Binding.STUDENTS_SOURCE) KStream<String, Student> students) {
        students.mapValues(student -> {
            Set<Group> groups = myService.getGroups();
            return ExtendedStudent.builder()
                                  .name(student.getName())
                                  .score(student.getScore())
                                  .groupsMatch(myService.getGroupsMatch(student, groups))
                                  .build();
        })
                .flatMapValues(value -> {
                    Set<ExtendedStudent> studentSet = new HashSet<>();
                    for (String id : value.getGroupsMatch().keySet()) {
                        studentSet.add(value.toBuilder()
                                            .groupsMatch(
                                                    Collections.singletonMap(id, value.getGroupsMatch().get(id)))
                                            .build());
                    }
                    return studentSet;
                })
                .selectKey((key, value) -> StuGro.builder()
                                                 .name(key)
                                                 .id(value.getGroupsMatch()
                                                          .keySet()
                                                          .iterator()
                                                          .next())
                                                 .build())
                .mapValues(value -> Objects.nonNull(value.getGroupsMatch().values().iterator().next())
                        ? value
                        : null)
                .to(Binding.CONNECTIONS_TOPIC, Produced.with(stuGroSerde, extendedStudentSerde));
    }

}
