package com.qingspring.demo.controller.DTO;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>qingspring</h3>
 * <p>modifyPassword</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-10 18:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDTO {
    @Alias("用户姓名")
    private String username;
    private String password;
    private String confirmPassword;
    private String newPassword;
}
