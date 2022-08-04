package com.qingspring.demo.utils.JWT;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.qingspring.demo.entity.User;
import com.qingspring.demo.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * <h3>qingspring</h3>
 * <p>JWTutils</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-27 18:38
 **/

@Component
public class TokenUtils {

//    过期时间
    private static final long EXPIRE_TIME = 120*60*1000;

    public static String getToken(String userId,String sign){
        Date date =new Date(System.currentTimeMillis()+EXPIRE_TIME);

        return JWT.create().withAudience(userId)   //将userId保存到token中
                .withExpiresAt(date)//5分钟后token过期
                .sign(Algorithm.HMAC256(sign));//用sign作为密钥进行加密 一般传password
    }

    private static IUserService staticUserService;
    @Resource
    private  IUserService userService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

//    获取当前登录的用户信息
//    可以在全局任何地方使用
//返回的是一个User 是当前请求的用户
    public static User getCurrentUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.parseInt(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }



}
