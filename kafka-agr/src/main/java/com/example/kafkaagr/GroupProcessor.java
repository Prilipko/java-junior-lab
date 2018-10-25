package com.example.kafkaagr;

import static java.util.Collections.singletonMap;

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
public class GroupProcessor {

    private final MyService myService;
    private final JsonSerde<StuGro> stuGroSerde;
    private final JsonSerde<ExtendedStudent> extendedStudentSerde;

    public GroupProcessor(final MyService myService) {
        this.myService = myService;
        stuGroSerde = new JsonSerde<>(StuGro.class);
        extendedStudentSerde = new JsonSerde<>(ExtendedStudent.class);
    }

    @StreamListener
//    @SendTo(Binding.STUDENTS_SINK)
    public void process(@Input(Binding.GROUPS_SOURCE) KStream<String, Group> groups) {
        groups.mapValues(group -> ExtendedGroup.builder()
                                               .id(group.getId())
                                               .maxScore(group.getMaxScore())
                                               .minScore(group.getMinScore())
                                               .oldMaxScore(group.getOldMaxScore())
                                               .oldMinScore(group.getOldMinScore())
                                               .students(myService.getStudentsByScores(group.getMinScore(),
                                                                                       group.getMaxScore()))
                                               .oldStudents(myService.getStudentsByScores(group.getOldMinScore(),
                                                                                          group.getOldMaxScore()))
                                               .build())
              .flatMapValues(value -> {
                  Set<ExtendedStudent> newMembers = new HashSet<>();
                  for (Student student : value.getStudents()) {
                      newMembers.add(ExtendedStudent
                                             .builder()
                                             .name(student.getName())
                                             .score(student.getScore())
                                             .groupsMatch(singletonMap(value.getId(),
                                                                       Group.builder()
                                                                            .id(value.getId())
                                                                            .maxScore(value.getMaxScore())
                                                                            .minScore(value.getMinScore())
                                                                            .oldMaxScore(value.getOldMaxScore())
                                                                            .oldMinScore(value.getOldMinScore())
                                                                            .build()))
                                             .build());
                  }
                  Set<ExtendedStudent> oldMembers = new HashSet<>();
                  for (Student student : value.getOldStudents()) {
                      oldMembers.add(ExtendedStudent
                                             .builder()
                                             .name(student.getName())
                                             .score(student.getScore())
                                             .groupsMatch(singletonMap(value.getId(), null))
                                             .build());
                  }
                  oldMembers.removeAll(newMembers);
                  newMembers.addAll(oldMembers);
                  return newMembers;
              })
              .selectKey((key, value) -> StuGro.builder()
                                               .id(value.getGroupsMatch()
                                                        .keySet()
                                                        .iterator()
                                                        .next())
                                               .name(value.getName())
                                               .build())
              .mapValues(value -> Objects.nonNull(value.getGroupsMatch().values().iterator().next())
                      ? value
                      : null)
              .to(Binding.CONNECTIONS_TOPIC, Produced.with(stuGroSerde, extendedStudentSerde));
    }

}
