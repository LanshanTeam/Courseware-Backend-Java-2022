package com.example.springbootlesson.controller;

import com.example.springbootlesson.bean.Result;
import com.example.springbootlesson.bean.Student;
import com.example.springbootlesson.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/request")
    public String testRequest(HttpServletRequest request) {
        return request.getParameter("name");
    }

    @GetMapping("/user/{id}")
    public Integer getId(@PathVariable Integer id) {
        return id;
    }

    @GetMapping("/user")
    public String getUserName(String name, String pwd) {
        return "name:" + name + "  pwd:" + pwd;
    }

    @GetMapping("/RequestParam")
    public String testRequestParam(@RequestParam(name = "pwd") String password, @RequestParam(name = "name") String username) {
        return "name:" + username + "  pwd:" + password;
    }

    @PostMapping("/requestBody")
    public User getUserJSON(@RequestBody User user) {
        return user;
    }



}
