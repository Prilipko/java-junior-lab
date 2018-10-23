/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.two_ktbl;

import java.util.HashSet;
import java.util.Set;

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
public class Teacher {
    String name;
    int age;
    Set<Student> students = new HashSet<>();
}
