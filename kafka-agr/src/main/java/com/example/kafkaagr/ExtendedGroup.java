package com.example.kafkaagr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedGroup {
    private String id;
    private int maxScore;
    private int minScore;
    Set<Student> students;
}
