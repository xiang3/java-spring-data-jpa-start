package com.example.demo.repository;

import com.example.demo.model.Grade;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findByGradeAndClassRoom(Grade grade, Integer classRoom);
}
