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
public class GroupProcessor {

    @NonNull
    private final MyService myService;

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
                  Set<ExtendedGroup> groupSet = new HashSet<>();
                  for (Student student : value.getStudents()) {
                      groupSet.add(value.toBuilder().students(Collections.singleton(student)).build());
                  }
                  Set<ExtendedGroup> oldGroupSet = new HashSet<>();
                  for (Student student : value.getOldStudents()) {
                      oldGroupSet.add(value.toBuilder().students(Collections.singleton(student)).build());
                  }
                  oldGroupSet.removeAll(groupSet);
                  groupSet.addAll(oldGroupSet);
                  return groupSet;
              })
              .selectKey((key, value) -> {
                  if (!value.getOldStudents().isEmpty()) {
                      Student student = value.getOldStudents().iterator().next();
                      return StuGro.builder().id(key).name(student.getName()).build();
                  }
                  Student student = value.getStudents().iterator().next();
                  return StuGro.builder().id(key).name(student.getName()).build();

              })
              .mapValues(value -> value.getOldStudents().isEmpty()
                      ? value
                      : null)
              .to(Binding.CONNECTIONS_TOPIC);
//              .filter((key, value) -> !value.getStudents().isEmpty())
//              .flatMapValues(extendedGroup -> {
//                  Set<ExtendedGroup> extendedGroups = new HashSet<>(extendedGroup.getStudents().size());
//                  for (Student student : extendedGroup.getStudents()) {
//                      extendedGroups.add(extendedGroup.toBuilder()
//                                                      .students(Collections.singleton(student))
//                                                      .build());
//                  }
//                  return extendedGroups;
//              }).
//              .groupBy()
//              .selectKey((key, value) -> value.getStudents().iterator().next().getName())
//              .groupByKey().aggregate()
//              .groupBy((key, value) -> {
//                  Student student = value.getStudents().iterator().next();
//                  Group group = Group.builder()
//                                     .id(value.getId())
//                                     .maxScore(value.getMaxScore())
//                                     .minScore(value.getMinScore())
//                                     .build();
//                  ExtendedStudent extendedStudent = ExtendedStudent.builder()
//                                                                   .name(student.getName())
//                                                                   .score(student.getScore())
//                                                                   .groups(Collections.singleton(group))
//                                                                   .build();
//
//                  return new KeyValue<>(student.getName(), extendedStudent);
//              });



    }

}
