/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.two_ktbl;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder(toBuilder = true)
public class Student {
    String name;
    int age;
    String teacher;
}
