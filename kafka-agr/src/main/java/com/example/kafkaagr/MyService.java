package com.example.kafkaagr;

import static org.apache.kafka.streams.state.QueryableStoreTypes.keyValueStore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import lombok.NonNull;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    private final Supplier<ReadOnlyKeyValueStore<String, Student>> studentsStore;
    private final Supplier<ReadOnlyKeyValueStore<String, Group>> groupsStore;
    private final Set<Group> groups;
    private final QueryableStoreRegistry queryableStoreRegistry;

    public MyService(@NonNull final QueryableStoreRegistry queryableStoreRegistry) {
        this.queryableStoreRegistry = queryableStoreRegistry;
        studentsStore =
                () -> this.queryableStoreRegistry.getQueryableStoreType(Binding.STUDENTS_STORE, keyValueStore());
        groupsStore = () -> this.queryableStoreRegistry.getQueryableStoreType(Binding.GROUPS_STORE, keyValueStore());

        groups = new HashSet<>();
        groups.add(Group.builder().id("gr1").minScore(0).maxScore(20).build());
        groups.add(Group.builder().id("gr2").minScore(20).maxScore(50).build());
        groups.add(Group.builder().id("gr3").minScore(50).maxScore(100).build());
        groups.add(Group.builder().id("gr4").minScore(10).maxScore(100).build());
    }

    @StreamListener
    public void groupsStoreSink(
            @Input(Binding.GROUPS_STORE_SINK) final KTable<String, Group> ignore) {
    }

    @StreamListener
    public void studentsStoreSink(
            @Input(Binding.STUDENTS_STORE_SINK) final KTable<String, Student> ignore) {
    }


//    public Set<Student> getStudentsByGroup(Group group) {
//        final Set<Student> associatedStudents = new HashSet<>();
//        final KeyValueIterator<String, Student> studentsStoreIterator = studentsStore.all();
//        while (studentsStoreIterator.hasNext()) {
//            final Student student = studentsStoreIterator.next().value;
//            if (student.getScore() <= group.getMaxScore() && student.getScore() >= group.getMinScore()) {
//                associatedStudents.add(student);
//            }
//        }
//        return associatedStudents;
//    }

    public Set<Student> getStudentsByScores(final int minScore, final int maxScore) {
        final Set<Student> associatedStudents = new HashSet<>();
        final KeyValueIterator<String, Student> studentsStoreIterator = studentsStore.get().all();
        while (studentsStoreIterator.hasNext()) {
            final Student student = studentsStoreIterator.next().value;
            if (student.getScore() <= maxScore && student.getScore() >= minScore) {
                associatedStudents.add(student);
            }
        }
        return associatedStudents;
    }

    public Set<Group> getGroupsByStudent(Student student) {
        final Set<Group> associatedGroups = new HashSet<>();
        final KeyValueIterator<String, Group> groupsStoreIterator = groupsStore.get().all();
        while (groupsStoreIterator.hasNext()) {
            final Group group = groupsStoreIterator.next().value;
            if (student.getScore() <= group.getMaxScore() && student.getScore() >= group.getMinScore()) {
                associatedGroups.add(group);
            }
        }
        return associatedGroups;
    }

    public Set<Group> getGroups() {
//        System.out.println(studentsStore.get());
//        System.out.println(groupsStore.get());

        final Set<Group> groups = new HashSet<>();
        final KeyValueIterator<String, Group> groupsStoreIterator = groupsStore.get().all();
        while (groupsStoreIterator.hasNext()) {
            groups.add(groupsStoreIterator.next().value);
        }
        return groups;
    }

    public Map<String, Group> getGroupsMatch(Student student, Set<Group> groups) {
        final Map<String, Group> groupsMatch = new HashMap<>();
//        final KeyValueIterator<String, Group> groupsStoreIterator = groupsStore.all();
        for (Group group : groups) {
            if (student.getScore() <= group.getMaxScore() && student.getScore() >= group.getMinScore()) {
                groupsMatch.put(group.getId(), group);
            } else {
                groupsMatch.put(group.getId(), null);
            }
        }
        return groupsMatch;
    }

}
