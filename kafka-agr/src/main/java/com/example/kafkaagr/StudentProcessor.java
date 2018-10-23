package com.example.kafkaagr;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentProcessor {

    @NonNull
    private final MyService myService;

    @StreamListener
    public void process(@Input(Binding.STUDENTS_SOURCE) KStream<String, Student> students) {
        students.mapValues(student -> ExtendedStudent.builder()
                                                     .name(student.getName())
                                                     .score(student.getScore())
                                                     .groupsMatch(myService.getGroupsMatch(student))
                                                     .build())
                .flatMapValues(value -> {
                    Set<ExtendedStudent> studentSet = new HashSet<>();
                    for (String id : value.getGroupsMatch().keySet()) {
                        studentSet.add(value.toBuilder()
                                            .groupsMatch(Collections.singletonMap(id, value.getGroupsMatch().get(id)))
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
                .mapValues(value -> value.getGroupsMatch().values().iterator().next()
                        ? value
                        : null)
                .to(Binding.CONNECTIONS_TOPIC);
    }

}
