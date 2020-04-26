package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String sex;

    private String Subject;

    @OneToMany(mappedBy = "teacher")
    private List<Student> students;
}
