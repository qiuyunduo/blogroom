package cn.qyd.blogroom.common.aop;

import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author qyd
 * @Date 19-3-1 上午9:37
 **/
@Component
@Aspect
@Slf4j
public class LoginHelper {

    @Pointcut("@annotation(cn.qyd.blogroom.common.annotations.CheckLogin)")
    public void login(){

    }

    @Before("login()")
    public void auth() {
        if(!LoginUtil.isLogin()) {
            throw BusinessException.fail(FrontRespEnum.LOGIN_INFO_EXIST);
        }
    }
}
