package com.qingspring.demo.common;

/**
* @author : EasyBreezyhs
* @date : 2022/07/16-9:34
*/
public enum ResponseEnum {

    SUCCESS("200","成功"),
    ERROR("500","系统错误"),

    USER_EXISTS("331","用户已存在"),
    REGISTER_DIFFERENT("332","两次密码不同"),

    TOKEN_EXISTS("333","无token,请重新登录"),
    TOKEN_FAIL("333","token验证失败，请重新登录"),
    TOKEN_USER_ERROE("333","用户不存在，请重新登录"),

    UPLOAD_FAIL("334","上传失败，重新上传"),
    FILE_EXCEED("334","文件过大，请选择不大于6mb的文件"),
    FILE_ISPIC("334","不是所支持的图片类型，请重新上传"),

    USER_INFO_NULL("300","用户信息不能为空"),
    EMAIL_ERROR("301","邮箱格式错误"),
    MOBILE_ERROR("302","手机格式错误"),
    USER_REGISTER_ERROR("304","用户注册失败"),
    USERNAME_NOT_EXISTS("310","用户名或密码错误"),
    PASSWORD_ERROR("306","密码错误"),
    PARAMETER_NULL("307","用户名或密码为空"),
    NOT_LOGIN("308","未登录"),

    USER_ADDRESS_ADD_ERROR("318","添加新地址失败"),
    USER_ADDRESS_SET_DEFAULT_ERROR("319","默认地址修改失败");


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private String code;
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}