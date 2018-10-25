package com.example.kafkaagr;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Serialized;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExtendedGroupProcessor {


    private final JsonSerde<ExtendedStudent> extendedStudentSerde;
    private final JsonSerde<ExtendedGroup> extendedGroupSerde;

    public ExtendedGroupProcessor() {
        extendedStudentSerde = new JsonSerde<>(ExtendedStudent.class);
        extendedGroupSerde = new JsonSerde<>(ExtendedGroup.class);
    }

    @StreamListener
    public void process(@Input(Binding.CONNECTIONS_GROUPS_PROCESSOR_SINK) KTable<StuGro, ExtendedStudent> students) {
        students.groupBy((key, value) -> new KeyValue<>(key.getId(), value),
                         Serialized.with(Serdes.String(), extendedStudentSerde))
                .aggregate(ExtendedGroup::new,
                           (key, value, aggregate) -> {
                               Group group = value.getGroupsMatch().values().iterator().next();
                               Student student = Student.builder()
                                                        .name(value.getName())
                                                        .score(value.getScore())
                                                        .build();
                               aggregate.setId(group.getId());
                               aggregate.setMaxScore(group.getMaxScore());
                               aggregate.setMinScore(group.getMinScore());
                               aggregate.getStudents().add(student);
                               return aggregate;
                           },
                           (key, value, aggregate) -> {
                               Group group = value.getGroupsMatch().values().iterator().next();
                               Student student = Student.builder()
                                                        .name(value.getName())
                                                        .score(value.getScore())
                                                        .build();
                               aggregate.setId(group.getId());
                               aggregate.setMaxScore(group.getMaxScore());
                               aggregate.setMinScore(group.getMinScore());
                               aggregate.getStudents().remove(student);
                               return aggregate;
                           },
                           Materialized.with(Serdes.String(), extendedGroupSerde))
                .toStream()
                .to(Binding.EXTENDED_GROUPS_TOPIC, Produced.with(Serdes.String(), extendedGroupSerde));
    }

}
//stud1~{"name":"stud1","score":10}
//gr1~{"id":"gr1","maxScore":20,"minScore":0,"oldMaxScore":0,"oldMinScore":0}