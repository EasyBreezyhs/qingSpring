package com.qingspring.demo.utils.JWT;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.entity.User;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <h3>qingspring</h3>
 * <p>JwtInterceptor</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-27 18:51
 **/
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }


    if (method.isAnnotationPresent(LoginToken.class)) {
        LoginToken userLoginToken = method.getAnnotation(LoginToken.class);
        if (userLoginToken.required()) {

            //执行认证
            if (StrUtil.isBlank(token)) {
                throw new ServiceException(ResponseEnum.TOKEN_EXISTS);
            }

            String userId;
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTException je) {
                throw new ServiceException(ResponseEnum.TOKEN_EXISTS);
            }

            //根据token中的userId查询数据库
            User user = userService.getById(userId);
            if (user == null) {
                throw new ServiceException(ResponseEnum.TOKEN_USER_ERROE);
            }

            //最后进行用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            try {
                //验证token
                jwtVerifier.verify(token);
            } catch (JWTException je) {
                throw new ServiceException(ResponseEnum.TOKEN_FAIL);
            }
            return true;
        }
    }

        return true;
    }





}
