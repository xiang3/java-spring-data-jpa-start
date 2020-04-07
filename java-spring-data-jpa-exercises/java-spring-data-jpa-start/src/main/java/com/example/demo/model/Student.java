package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Student extends Person {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade = Grade.FIRST_GRADE;

    @Column(nullable = false, name = "class_room")
    private Integer classRoom;

    public Student(String name, Integer age, Sex sex, Grade grade, Integer classRoom) {
        super(name, age, sex);
        this.grade = grade;
        this.classRoom = classRoom;
    }

    public Student() {}
}
