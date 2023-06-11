package com.rabbitmq.rabbitmq.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/3/19
 * @Email: 2825097536@qq.com
 */
@Data
@AllArgsConstructor
@ToString()
public class Animal implements Serializable {
        private static final long serialVersionUID = 11111L;
    String name;
    int age;
    int sex;
}
