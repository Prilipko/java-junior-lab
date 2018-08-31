
package com.example.kfkstrm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupQ {

    Long id;
    Integer minAge;
    Integer maxAge;
    Integer minHeight;
    Integer maxHeight;
}
