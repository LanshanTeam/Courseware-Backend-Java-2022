package com.yxyl.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxyl.springboot.mapper.UserMapper;
import com.yxyl.springboot.model.User;
import com.yxyl.springboot.model.auth.LoginUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            System.out.println("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        //TODO 查询权限信息封装
        return new LoginUser(user);
    }
}
