package com.example.springbootlesson.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
@ConfigurationProperties(prefix = "student")
@Data
public class Student {

    String name;
    Integer age;
    Float weight;
    String[] likes;



}
