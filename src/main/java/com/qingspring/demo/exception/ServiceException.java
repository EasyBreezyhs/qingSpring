package com.qingspring.demo.exception;

import com.qingspring.demo.common.ResponseEnum;
import lombok.Data;

/**
 * <h3>qingspring</h3>
 * <p>GlobalException</p>
 *  自定义异常类
 * @author : EasyBreezyhs
 * @date : 2022-07-16 09:59
 **/
@Data
public class ServiceException extends RuntimeException{
    private static final long SERIVAL_VERSIONUID = 1L;

//    错误码
    private String errorCode;
//    错误信息
    private String errorMsg;

    public ServiceException(){
        super();
    }

    public ServiceException(String errorMsg){
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ServiceException(String errorCode, String errorMsg){
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ServiceException(String errorCode, String errorMsg, Throwable cause){
        super(errorCode,cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }



    public ServiceException(ResponseEnum responseEnum){
        super(responseEnum.getCode());
        this.errorMsg = responseEnum.getMsg();
        this.errorCode = responseEnum.getCode();
    }

    public ServiceException(ResponseEnum responseEnum, Throwable cause){
        super(responseEnum.getCode(),cause);
        this.errorMsg = responseEnum.getMsg();
        this.errorCode = responseEnum.getCode();
    }

/****
 *java.lang.Throwable类的fillInStackTrace()方法在此Throwable对象中记录有关当前线程的堆栈帧的当前状态的信息。
 *这意味着使用此方法可以看到类的当前方法的异常消息，其中调用了fillInStackTrace()方法。
 *如果还有其他消息可以从当前方法派生而抛出异常，则可以跳过这些其他消息详细信息。
*/
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }



}
