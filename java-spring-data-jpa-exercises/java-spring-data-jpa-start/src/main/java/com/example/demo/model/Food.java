package com.example.demo.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Food {
    @Id
    @GeneratedValue
    private Integer id;

//    @Column(nullable = false)
    private String name;

//    @Column(nullable = false, unique = true)
    @Enumerated(value =  EnumType.ORDINAL)
    private Star star;

//    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StyleOfCooking styleOfCooking;

//    @Column(nullable = false)
    private Double price;

    public Food(String name, Double price, Star star, StyleOfCooking styleOfCooking) {
        this.name = name;
        this.price = price;
        this.star = star;
        this.styleOfCooking = styleOfCooking;
    }

    public Food() {}
}
