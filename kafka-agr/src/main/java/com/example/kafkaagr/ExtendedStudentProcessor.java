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
public class ExtendedStudentProcessor {


    private final JsonSerde<ExtendedStudent> extendedStudentSerde;

    public ExtendedStudentProcessor() {
        extendedStudentSerde = new JsonSerde<>(ExtendedStudent.class);
    }

    @StreamListener
    public void process(@Input(Binding.CONNECTIONS_STUDENTS_PROCESSOR_SINK) KTable<StuGro, ExtendedStudent> students) {
        students.groupBy((key, value) -> new KeyValue<>(key.getName(), value),
                         Serialized.with(Serdes.String(), extendedStudentSerde))
                .aggregate(ExtendedStudent::new,
                           (key, value, aggregate) -> {
                               Group group = value.getGroupsMatch().values().iterator().next();
                               aggregate.setName(value.getName());
                               aggregate.setScore(value.getScore());
                               aggregate.getGroupsMatch().put(group.getId(), group);
                               return aggregate;
                           },
                           (key, value, aggregate) -> {
                               Group group = value.getGroupsMatch().values().iterator().next();
                               aggregate.setName(value.getName());
                               aggregate.setScore(value.getScore());
                               aggregate.getGroupsMatch().remove(group.getId());
                               return aggregate;
                           },
                           Materialized.with(Serdes.String(), extendedStudentSerde))
                .toStream()
                .to(Binding.EXTENDED_STUDENTS_TOPIC, Produced.with(Serdes.String(), extendedStudentSerde));
    }
}
//stud1~{"name":"stud1","score":10}