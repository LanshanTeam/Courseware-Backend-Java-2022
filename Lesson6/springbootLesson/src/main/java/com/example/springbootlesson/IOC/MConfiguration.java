package com.example.springbootlesson.IOC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MConfiguration {

    @Bean
    public Engine electricEngine(){
        return ()->{
            System.out.println("electricEngine is running...");
        };
    }
}
