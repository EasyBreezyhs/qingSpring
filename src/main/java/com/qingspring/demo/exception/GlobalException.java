package com.qingspring.demo.exception;

import com.qingspring.demo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <h3>qingspring</h3>
 * <p>GlobalExvception</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-16 10:55
 **/
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se){
        return Result.error(se.getErrorCode(), se.getErrorMsg());
    }


}
