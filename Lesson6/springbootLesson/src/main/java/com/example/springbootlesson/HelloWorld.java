package com.example.springbootlesson;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorld {


    @GetMapping
    public String helloWorld() {
        return "hello world";
    }

}
