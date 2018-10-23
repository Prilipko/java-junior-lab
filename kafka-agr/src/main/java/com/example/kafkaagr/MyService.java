package com.example.kafkaagr;

import static com.example.kafkaagr.Binding.GROUPS_STORE;
import static com.example.kafkaagr.Binding.STUDENTS_STORE;
import static org.apache.kafka.streams.state.QueryableStoreTypes.keyValueStore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.NonNull;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final ReadOnlyKeyValueStore<String, Student> studentsStore;
    private final ReadOnlyKeyValueStore<String, Group> groupsStore;

    public MyService(@NonNull final QueryableStoreRegistry queryableStoreRegistry) {
        studentsStore = queryableStoreRegistry.getQueryableStoreType(STUDENTS_STORE, keyValueStore());
        groupsStore = queryableStoreRegistry.getQueryableStoreType(GROUPS_STORE, keyValueStore());
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

//    public Set<Group> getGroupsByStudent(Student student) {
//        final Set<Group> associatedGroups = new HashSet<>();
//        final KeyValueIterator<String, Group> groupsStoreIterator = groupsStore.all();
//        while (groupsStoreIterator.hasNext()) {
//            final Group group = groupsStoreIterator.next().value;
//            if (student.getScore() <= group.getMaxScore() && student.getScore() >= group.getMinScore()) {
//                associatedGroups.add(group);
//            }
//        }
//        return associatedGroups;
//    }

    public Map<String, Boolean> getGroupsMatch(Student student) {
        final Map<String, Boolean> groupsMatch = new HashMap<>();
        final KeyValueIterator<String, Group> groupsStoreIterator = groupsStore.all();
        while (groupsStoreIterator.hasNext()) {
            final Group group = groupsStoreIterator.next().value;
            if (student.getScore() <= group.getMaxScore() && student.getScore() >= group.getMinScore()) {
                groupsMatch.put(group.getId(), true);
            } else {
                groupsMatch.put(group.getId(), false);
            }
        }
        return groupsMatch;
    }

    @StreamListener
    public void groupsStoreSink(
            @Input(Binding.GROUPS_STORE_SINK) final KTable<String, Group> ignore) {
    }

}
