
package com.example.kfkstrm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitEventEx{

    Long id;
    Long studentId;
    Student student;
    Long roomId;
    Room room;
    Long duration;
    Long additionalVisitId;
    VisitEventEx additionalVisit;
}
