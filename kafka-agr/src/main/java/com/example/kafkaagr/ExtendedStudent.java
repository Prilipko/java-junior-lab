package com.example.kafkaagr;

import java.util.Map;
import java.util.Set;

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
    private Set<Group> groups;
    private Map<String, Boolean> groupsMatch;
}
