package cn.qyd.blogroom.common.exception;

import cn.qyd.blogroom.common.resp.code.CodeAndMessage;
import cn.qyd.blogroom.common.resp.code.RespCodeAndMessageEnum;

public class BusinessException extends RuntimeException{
    private CodeAndMessage respCodeAndMessageEnum;

    public static BusinessException fail(CodeAndMessage respCodeAndMessageEnum){
        return new BusinessException(respCodeAndMessageEnum);
    }

    public BusinessException(){
        super();
        this.respCodeAndMessageEnum = RespCodeAndMessageEnum.SYSTEM_ERROR;
    }

    public BusinessException(CodeAndMessage respCodeAndMessageEnum){
        this.respCodeAndMessageEnum = respCodeAndMessageEnum;
    }


    public CodeAndMessage getRespCodeAndMessageEnum() {
        return respCodeAndMessageEnum;
    }

    public void setRespCodeAndMessageEnum(RespCodeAndMessageEnum respCodeAndMessageEnum) {
        this.respCodeAndMessageEnum = respCodeAndMessageEnum;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
