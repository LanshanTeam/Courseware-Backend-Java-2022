package com.example.springbootlesson;

import com.example.springbootlesson.IOC.Car;
import com.example.springbootlesson.bean.Student;
import com.example.springbootlesson.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLessonApplicationTests {


    @Autowired
    Car car;

    @Test
    public void testIOC() {
        car.run();
    }

    @Autowired
    TestMapper testMapper;

    @org.junit.jupiter.api.Test
    public void testSelect() {
        System.out.println(testMapper.selectAll());
    }

    @Test
    public void testSelectByGenderAndHomeAddr() {
        System.out.println(testMapper.selectByGenderAndHomeAddr("男", "北京"));
    }


    @Value("${fruit}")
    String fruitName;

    @Value("${student.name}")
    String name;

    @Test
    public void testYAML() {
        System.out.println(fruitName);
        System.out.println(name);
    }

    @Autowired
    Student student;

    @Test
    public void testConfigurationProperties() {
        System.out.println(student);
    }
}
