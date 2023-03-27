package com.yxyl.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxyl.springboot.mapper.UserMapper;
import com.yxyl.springboot.model.User;
import com.yxyl.springboot.model.auth.LoginUser;
import com.yxyl.springboot.model.auth.UserResponse;
import com.yxyl.springboot.service.UserService;
import com.yxyl.springboot.utils.JwtUtil;
import com.yxyl.springboot.utils.RedisCache;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RedisCache redisCache;

    private static final String REDIS_KEY = "USER_LOGIN:";


    @Override
    public List<User> listAll() {
        System.out.println("SecurityContextHolder.getContext().getAuthentication().getPrincipal() = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<User> list = list();
        return list;
    }

    @Override
    public UserResponse login(User user) {
        UsernamePasswordAuthenticationToken
                authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String studentId = loginUser.getUser().getId().toString();

        String token = JwtUtil.createJWT(studentId);
        
        //这里还可以吧loginUser信息放在Redis里面，方便以后的功能模块会用到
        redisCache.setCacheObject(REDIS_KEY + studentId, loginUser, 30, TimeUnit.MINUTES);

        return new UserResponse(token, loginUser.getUser());
    }

    @Override
    public User regis(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        return user;
    }
}
