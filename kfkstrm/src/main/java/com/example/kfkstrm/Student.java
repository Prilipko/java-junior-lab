
package com.example.kfkstrm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student{

    public Long id;
    public String name;
    public int age;
    public int height;
}
