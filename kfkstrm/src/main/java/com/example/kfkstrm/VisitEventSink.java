/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.kfkstrm;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.11
 */
//@Component
@Slf4j
public class VisitEventSink {

//    @StreamListener(KfkBinding.VISIT_IN)
    public void process(@Input(KfkBinding.VISIT_IN) KStream<Long, VisitEvent> events) {
        events.peek((key, value) -> log.info("value {}", value));
    }
}
