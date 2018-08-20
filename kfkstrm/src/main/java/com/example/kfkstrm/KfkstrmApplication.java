package com.example.kfkstrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KfkstrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(KfkstrmApplication.class, args);
    }
}



class Student{
    Long id;
    String name;
}

class Room{
    Long id;
    String name;
}

class VisitEvent{
    Long id;
    Long studentId;
    Long roomId;
    Long duration;
    Long additionalVisitId;
}

class VisitEventEx{
    Long id;
    Student studentId;
    Room roomId;
    Long duration;
    VisitEventEx additionalVisit;
}