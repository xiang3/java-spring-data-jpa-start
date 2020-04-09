package com.example.demo.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(value = "customer")
@Getter
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;

    private String city;

    private String career;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Food> foods;

    public Customer() {
    }

    public Customer(String city, String career, List<Food> foods) {
//        super(name, age, sex);
        this.city = city;
        this.career = career;
        this.foods = foods;
    }
}
