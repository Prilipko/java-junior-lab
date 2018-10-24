package com.example.kafkaagr;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedStudent {
    private String name;
    private int score;
    private Map<String, Group> groupsMatch = new HashMap<>();
}
