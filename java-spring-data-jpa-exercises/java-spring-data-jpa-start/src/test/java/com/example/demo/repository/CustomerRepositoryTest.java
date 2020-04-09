package com.example.demo.repository;

import com.example.demo.model.*;
import org.assertj.core.util.Lists;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxyHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional
    public void one_to_many_operator() {
        List<Food> foods = Lists.newArrayList(
                new Food("huiguorou", 20.0, Star.THREE, StyleOfCooking.SICHUAN_CUISHINE),
                new Food("yuxiangroushi", 23.0, Star.ONE, StyleOfCooking.SICHUAN_CUISHINE),
                new Food("jinjiangrousi", 21.0, Star.TWO, StyleOfCooking.BEIJING_CUISHINE),
                new Food("roujiamo", 5.0, Star.THREE, StyleOfCooking.SHANNXI_CUISHINE)
        );

        Customer customer = new Customer( "shannxi", "programmer", foods);
        customerRepository.save(customer);
        List<Customer> customers = customerRepository.findAll();

        Food foods1 = customers.get(0).getFoods().get(0);
        Assert.assertEquals(1, customers.size());
        Assert.assertEquals("huiguorou", foods1.getName());

        customerRepository.delete(customer);
        List<Customer> customers1 = customerRepository.findAll();
        Assert.assertEquals(0, customers1.size());
    }
}