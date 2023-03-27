package com.yxyl.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("/com/yxyl/springboot/mapper")
@SpringBootApplication
public class SpringSecurity2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity2Application.class, args);
    }

}
