
package com.example.kfkstrm;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroup {

    Long id;
    Integer minAge;
    Integer maxAge;
    Integer maxHeight;
    Integer minHeight;

    List<Student> students;
}
