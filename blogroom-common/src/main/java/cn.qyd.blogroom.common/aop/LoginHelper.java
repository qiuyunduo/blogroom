package cn.qyd.blogroom.common.aop;

import cn.qyd.blogroom.common.constants.Constant;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.TokenUtil;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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

//    @Pointcut("execution(* cn.qyd.blogroom.*.controller.*.*(..))")
//    public void login1(){
//
//    }

    @Before("login()")
    public void authUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Constant.USER_TOKEN_KEY);
        if(StringUtils.isEmpty(token)) {
            throw BusinessException.fail(FrontRespEnum.LOGIN_INFO_EXIST);
        }
    }

}
