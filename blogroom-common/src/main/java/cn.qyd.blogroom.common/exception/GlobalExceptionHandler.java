package cn.qyd.blogroom.common.exception;

import cn.qyd.blogroom.common.resp.Resp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static cn.qyd.blogroom.common.resp.code.RespCodeAndMessageEnum.PARAMS_ERROR;

/**
 * controller抛出异常捕捉
 */
@ControllerAdvice({
        "cn.qyd.blogroom.user.controller",
        "cn.qyd.blogroom.blog.controller",
        "cn.qyd.blogroom.article.controller",
        "cn.qyd.blogroom.website.controller",
})
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 业务异常拦截
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Resp businessErrorHandler(BusinessException ex){
        log.info("code={} msg={}",ex.getRespCodeAndMessageEnum().getCode(),ex.getRespCodeAndMessageEnum().getMessage());
        return Resp.fail(ex.getRespCodeAndMessageEnum());
    }

    /**
     * bean对象校验异常捕捉
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Resp beanValidErrorHandler(MethodArgumentNotValidException ex){
        log.debug("bean对象校验异常捕捉");
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(e -> {
            sb.append(e.getField()+":"+e.getDefaultMessage()+";");
        });

        Resp resp = Resp.fail(PARAMS_ERROR.getCode(),sb.toString());
        return resp;
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Resp errorHandler(Exception ex){
        log.debug("打印全局异常！");
        log.error(ex.getMessage(),ex);
        Resp resp = Resp.fail(ex.getMessage());
        return  resp;
    }


}
