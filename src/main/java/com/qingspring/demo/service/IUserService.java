package com.qingspring.demo.service;

import com.qingspring.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.math3.analysis.function.Exp;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-07-07
 */
public interface IUserService extends IService<User> {

    Boolean exp(HttpServletResponse response,List<User> userList) throws Exception;


}
