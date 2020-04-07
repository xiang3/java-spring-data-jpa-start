package com.example.demo.repository;

import com.example.demo.model.Person;
import com.example.demo.model.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public List<Person> getAllByIdOrderById(Integer id);

    public List<Person> getByAgeGreaterThan(Integer age);

    @Query(value = "select p from Person p where p.age >= 18 order by age desc")
    public List<Person> getAllByGrowmUpStudentOrderByAge();

    public List<Person> getBySexEquals(@Param("sex") Sex sex);

    public List<Person> getByAgeEquals(Integer age);
}
