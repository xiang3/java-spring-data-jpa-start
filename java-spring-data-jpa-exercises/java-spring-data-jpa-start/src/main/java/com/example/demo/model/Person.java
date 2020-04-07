package com.example.demo.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="country", discriminatorType = DiscriminatorType.STRING, length = 20)
public class Person {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    private Integer age=0;

    @Enumerated(EnumType.ORDINAL)
    private Sex sex = Sex.MALE;

    public Person(String name, Integer age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public Integer getId() {
        return id;
    }
}
