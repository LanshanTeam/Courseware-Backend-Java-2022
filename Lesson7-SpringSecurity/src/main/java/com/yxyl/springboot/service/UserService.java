package com.yxyl.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxyl.springboot.model.User;
import com.yxyl.springboot.model.auth.UserResponse;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listAll();

    UserResponse login(User user);

    User regis(User user);
}
