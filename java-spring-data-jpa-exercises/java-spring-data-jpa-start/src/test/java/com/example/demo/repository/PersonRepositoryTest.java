package com.example.demo.repository;

import com.example.demo.model.Person;
import com.example.demo.model.Sex;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    public PersonRepository personRepository;

    @Test
    public void should_get_data_when_operator_database() {
        List<Person> persons = Lists.newArrayList(
                new Person("xiang", 21, Sex.MALE),
                new Person("rou", 20, Sex.FEMALE),
                new Person("zhao", 19, Sex.MALE),
                new Person("li", 17, Sex.FEMALE),
                new Person("ma", 18, Sex.FEMALE)
        );
        personRepository.saveAll(persons);

        List<Person> adutStudent = personRepository.getAllByGrowmUpStudentOrderByAge();
        Assert.assertEquals(4, adutStudent.size());
        Assert.assertEquals(Integer.valueOf(21), adutStudent.get(0).getAge());

        List<Person> idIsOneStudent = personRepository.getAllByIdOrderById(1);
        Assert.assertEquals(1, idIsOneStudent.size());
        Assert.assertEquals("xiang", idIsOneStudent.get(0).getName());

        List<Person> AgeGreaterThanTwiceStudent = personRepository.getByAgeGreaterThan(20);
        Assert.assertEquals(Integer.valueOf(21), AgeGreaterThanTwiceStudent.get(0).getAge());

        List<Person> maleStudent = personRepository.getBySexEquals(Sex.MALE);
        Assert.assertEquals(2, maleStudent.size());
        Assert.assertEquals("xiang", maleStudent.get(0).getName());
    }
}