package com.example.booksystem.common;

import cn.hutool.core.util.StrUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.booksystem.entity.User;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterception implements HandlerInterceptor {

    private static final String ERR_CODE_401 = "401";

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token =request.getParameter("Token");
        if(StrUtil.isBlank(token)){
            token = request.getHeader("Token");
        }

        if(StrUtil.isBlank(token)){
            throw new ServiceException(ERR_CODE_401, "请登录");

        }

        String UserId;
        User user;

        try {

            UserId = JWT.decode(token).getAudience().get(0);
            user = userService.getUserById(UserId);
        }catch (Exception e){
            String errMsg = "token验证失败, 请重新登录";
            throw new ServiceException(ERR_CODE_401, errMsg);
        }
        if(user == null){
            throw new ServiceException(ERR_CODE_401, "用户不存在, 请重新登录");

        }

        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token);

        }catch (JWTVerificationException e){
            throw new ServiceException(ERR_CODE_401, "token验证失败, 请重新登录");

        }
        return true;
    }
}
