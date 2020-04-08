package com.example.demo.repository;

import com.example.demo.model.Person;
import com.example.demo.model.Sex;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PageableSqlTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void use_page_request_test() {
        List<Person> persons = Lists.newArrayList(
                new Person("xiang", 21, Sex.FEMALE),
                new Person("rou", 22, Sex.FEMALE),
                new Person("zhao", 23, Sex.FEMALE),
                new Person("wang", 24, Sex.FEMALE),
                new Person("chen", 34, Sex.MALE),
                new Person("fan", 5, Sex.MALE),
                new Person("zhang", 43, Sex.MALE),
                new Person("cheng", 5, Sex.MALE),
                new Person("li", 12, Sex.MALE)
        );
        personRepository.saveAll(persons);

        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<Person> personPage = personRepository.findAll(pageRequest);
        Assert.assertEquals(9, personPage.getTotalElements()); // 总元素
        Assert.assertEquals(1, personPage.getNumber()); // 当前页标
        Assert.assertEquals(5, personPage.getSize());// 一页的元素个数
        Assert.assertEquals(2, personPage.getTotalPages()); // 总页数
        Assert.assertEquals(4, personPage.get().count()); // 当前页总数量
        Assert.assertEquals(0, personPage.previousPageable().getOffset()); //上一页的偏移量
        Assert.assertEquals("fan", personPage.iterator().next().getName()); //当前页中的第一个元素名字

        Page<Person> lessThan30YearOldPerson = personRepository.getByAgeLessThan(30, PageRequest.of(1, 2)); // 通过参数中增加PageRequest可以进行分页Jpa可以自适应
        Assert.assertEquals(7, lessThan30YearOldPerson.getTotalElements());
        Assert.assertEquals(4, lessThan30YearOldPerson.getTotalPages());
        Assert.assertEquals("zhao", lessThan30YearOldPerson.iterator().next().getName());
    }
}
