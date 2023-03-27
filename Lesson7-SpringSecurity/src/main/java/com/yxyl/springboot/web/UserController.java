package com.yxyl.springboot.web;

import com.yxyl.springboot.model.User;
import com.yxyl.springboot.model.auth.UserResponse;
import com.yxyl.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.listAll();
    }

    
    @PostMapping("/login")
    public UserResponse login(@RequestBody User user) {
        if (user == null) {
            throw new RuntimeException("user can not be null");
        }
        return userService.login(user);
    }
    
    
    @PostMapping("/regis")
    public User regis(@RequestBody User user) {
        if (user == null) {
            throw new RuntimeException("user can not be null");
        }
        return userService.regis(user);
    }
}
