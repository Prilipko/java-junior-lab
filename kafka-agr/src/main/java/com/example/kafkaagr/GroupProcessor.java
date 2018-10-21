package com.example.kafkaagr;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GroupProcessor {



    @StreamListener
    @SendTo(Binding.STUDENTS_SINK)
    public KStream<String, Student> process(
            @Input(Binding.GROUPS_SOURCE) KTable<String, Group> groups){

    }

}
