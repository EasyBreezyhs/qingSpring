package com.qingspring.demo.controller.DTO;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qingspring.demo.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private Integer id;
    @Alias("用户姓名")
    private String username;
    @Alias("用户密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Alias("昵称")
    private String nickname;
    @Alias("头像")
    private String avatarUrl;
    @Alias("角色")
    private String role;
    @Alias("菜单")
    private List<Menu> menuList;


//    前端同一页面实现了登录和注册功能
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String reusername;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String repassword;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String reregpassword;

//    携带token
    private String token;

    //    携带token
    private String refreshToken;


}
