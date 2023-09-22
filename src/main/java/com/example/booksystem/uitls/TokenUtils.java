package com.example.booksystem.uitls;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.booksystem.core.entity.User;
import com.example.booksystem.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Objects;

@Component
public class TokenUtils {

    private static UserService staticUserService;

    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

    public static String generateToken(String uid, String sign){
        return JWT.create()
                .withAudience(uid)
                .withExpiresAt(DateUtil.offsetDay(new Date(), 3))
                .sign(Algorithm.HMAC256(sign));

    }

    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest() ;
            String token = request.getHeader("token");
            if(StrUtil.isBlank(token)){
                return null;
            }
            String UserId = JWT.decode(token).getAudience().get(0);
            return staticUserService.getUserById(UserId);
        }catch (Exception e){
            return null;
        }

    }
}
