package com.yxyl.springboot.filter;

import com.yxyl.springboot.model.auth.LoginUser;
import com.yxyl.springboot.utils.JwtUtil;
import com.yxyl.springboot.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String REDIS_KEY = "USER_LOGIN:";
    @Resource
    RedisCache redisCache;


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        String token = request.getHeader("token");
        if (null == token || "".equals(token)) {
            // token不存在 放行 并且直接return 返回
            filterChain.doFilter(request, response);
            return;
        }
        // 解析toke
        String userId = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
        // 获取userid 从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(REDIS_KEY + userId);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        //将用户信息存入到SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }
}