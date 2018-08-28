/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.kfkstrm;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class VisitEventExSink {

    @StreamListener(KfkBinding.VISIT_EX_IN)
    public void process(@Input(KfkBinding.VISIT_EX_IN) KStream<Long, VisitEventEx> events) {
        events.peek((key, value) -> log.info("mapped: {}", value));
    }
}
