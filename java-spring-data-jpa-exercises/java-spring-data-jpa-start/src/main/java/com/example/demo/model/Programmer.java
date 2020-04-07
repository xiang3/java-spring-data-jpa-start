package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "shannxi")
public class Programmer extends Person {
    @Column(nullable = false)
    private Boolean hasHair;

    private String skill;

    public Programmer(String name, Integer age, Sex sex, Boolean hasHair, String skill) {
        super(name, age, sex);
        this.hasHair = hasHair;
        this.skill = skill;
    }

    public Programmer() {}
}
