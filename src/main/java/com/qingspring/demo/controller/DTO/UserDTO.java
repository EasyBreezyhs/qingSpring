package com.qingspring.demo.controller.DTO;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>qingspring</h3>
 * <p>UserDTO</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-16 09:18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Alias("用户姓名")
    private String username;
    @Alias("用户密码")
    private String password;
    @Alias("昵称")
    private String nickname;
    @Alias("头像")
    private String avatarUrl;


}
