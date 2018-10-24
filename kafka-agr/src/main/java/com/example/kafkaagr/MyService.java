package com.example.kafkaagr;

import static com.example.kafkaagr.Binding.GROUPS_STORE;
import static com.example.kafkaagr.Binding.STUDENTS_STORE;
import static org.apache.kafka.streams.state.QueryableStoreTypes.keyValueStore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.NonNull;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final ReadOnlyKeyValueStore<String, Student> studentsStore;
    private final ReadOnlyKeyValueStore<String, Group> groupsStore;
    private final Set<Group> groups;

    public MyService(@NonNull final QueryableStoreRegistry queryableStoreRegistry) {
        studentsStore = queryableStoreRegistry.getQueryableStoreType(STUDENTS_STORE, keyValueStore());
        groupsStore = queryableStoreRegistry.getQueryableStoreType(GROUPS_STORE, keyValueStore());

        groups = new HashSet<>();
        groups.add(Group.builder().id("gr1").minScore(0).maxScore(20).build());
        groups.add(Group.builder().id("gr2").minScore(20).maxScore(50).build());
        groups.add(Group.builder().id("gr3").minScore(50).maxScore(100).build());
        groups.add(Group.builder().id("gr4").minScore(10).maxScore(100).build());
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
        final KeyValueIterator<String, Student> studentsStoreIterator = studentsStore.all();
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
        final KeyValueIterator<String, Group> groupsStoreIterator = groupsStore.all();
        while (groupsStoreIterator.hasNext()) {
            final Group group = groupsStoreIterator.next().value;
            if (student.getScore() <= group.getMaxScore() && student.getScore() >= group.getMinScore()) {
                associatedGroups.add(group);
            }
        }
        return associatedGroups;
    }

    public Set<Group> getGroups() {
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
