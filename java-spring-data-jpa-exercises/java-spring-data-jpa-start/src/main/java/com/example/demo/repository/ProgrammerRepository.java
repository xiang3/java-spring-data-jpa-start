package com.example.demo.repository;

import com.example.demo.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammerRepository extends JpaRepository<Programmer, Integer> {
    public List<Programmer> getAllByAgeEquals(Integer age);
}
