package com.example.demo.repository;

import com.example.demo.model.*;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProgrammerRepositoryTest {
    @Autowired
    ProgrammerRepository programmerRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void should_get_data_when_operator_database() {
        List<Student> students = Lists.newArrayList(
                new Student("xiang", 21, Sex.MALE, Grade.FIRST_GRADE, 1),
                new Student("rou", 19, Sex.FEMALE, Grade.FIRST_GRADE, 2),
                new Student("yang", 19, Sex.FEMALE, Grade.SECOND_GRADE, 1)
        );
        studentRepository.saveAll(students);

        List<Programmer> programmers = Lists.newArrayList(
                new Programmer("xiang", 21, Sex.MALE, false, "c++, python, java"),
                new Programmer("rou", 21, Sex.FEMALE, true, "javascript, java")
        );
        programmerRepository.saveAll(programmers);

        List<Person> twentyOneAgePerson = personRepository.getByAgeEquals(21);

        List<Programmer> twentyOneYearAgeProgrammer = programmerRepository.getAllByAgeEquals(21);
        Assert.assertEquals(2, twentyOneYearAgeProgrammer.size());
        Assert.assertEquals("xiang", twentyOneYearAgeProgrammer.get(0).getName());

        Assert.assertEquals(3, twentyOneAgePerson.size());
        Assert.assertEquals(2, twentyOneAgePerson.stream().filter(person -> person.getName().equals("xiang")).count());
    }
}