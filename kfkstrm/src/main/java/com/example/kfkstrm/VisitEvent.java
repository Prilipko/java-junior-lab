/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.kfkstrm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitEvent{

    Long id;
    Long studentId;
    Long roomId;
    Long duration;
    Long additionalVisitId;
}
