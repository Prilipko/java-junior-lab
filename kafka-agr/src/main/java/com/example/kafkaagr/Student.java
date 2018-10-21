package com.example.kafkaagr;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int score;
}
