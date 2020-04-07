package com.example.demo.repository;

import com.example.demo.model.Grade;
import com.example.demo.model.Sex;
import com.example.demo.model.Student;
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
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void should_get_data_when_operator_database() {
        List<Student> students = Lists.newArrayList(
                new Student("xiang", 21, Sex.MALE, Grade.FIRST_GRADE, 1),
                new Student("rou", 21, Sex.FEMALE, Grade.FIRST_GRADE, 2),
                new Student("yang", 21, Sex.FEMALE, Grade.SECOND_GRADE, 1)
        );
        studentRepository.saveAll(students);

        List<Student> firstGradeAndClassRoomStudent = studentRepository.findByGradeAndClassRoom(Grade.FIRST_GRADE, 1);
        Assert.assertEquals(1, firstGradeAndClassRoomStudent.size());
        Assert.assertEquals("xiang", firstGradeAndClassRoomStudent.get(0).getName());
    }
}