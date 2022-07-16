package com.qingspring.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;

/**
 * <h3>qingspring</h3>
 * <p> Result return</p>
 *  自定义数据传输
 * @author : EasyBreezyhs
 * @date : 2022-07-16 09:27
 **/
@Data
@NoArgsConstructor
public class Result {

    private Object data;
    private String code;
    private String msg;


    public static Result success(){
        return new Result(ResponseEnum.SUCCESS, null);
    }
    public static Result success(Object data){
        return new Result(ResponseEnum.SUCCESS, data);
    }


//    ERROR
    public static Result error(){
        return new Result(ResponseEnum.ERROR,null );
    }
    public static Result error(String msg){
        return new Result("-1",msg,null);
    }
    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }
    public static Result error(ResponseEnum responseEnum,Object data){
        return new Result(responseEnum,data);
    }



    public Result(ResponseEnum responseEnum,Object data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.data = data;
    }

    public Result(String code, String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
