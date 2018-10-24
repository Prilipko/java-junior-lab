package com.example.kafkaagr;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedGroup {
    private String id;
    private int maxScore;
    private int minScore;
    private int oldMaxScore;
    private int oldMinScore;
    Set<Student> students = new HashSet<>();
    Set<Student> oldStudents = new HashSet<>();;
}
