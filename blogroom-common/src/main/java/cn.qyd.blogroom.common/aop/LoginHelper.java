package cn.qyd.blogroom.common.aop;

import cn.qyd.blogroom.common.constants.Constant;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.TokenUtil;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
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
    @Autowired
    private TokenUtil tokenUtil;

    @Pointcut("@annotation(cn.qyd.blogroom.common.annotations.CheckLogin)")
    public void login(){

    }

    @Pointcut("execution(* cn.qyd.blogroom.*.controller.*.*(..))")
    public void login1(){

    }

    @Before("login()")
    public void authUser() {

        if(!TokenUtil.isLogin()) {
            throw BusinessException.fail(FrontRespEnum.LOGIN_INFO_EXIST);
        }
    }

    @Before("login1()")
    public void validate() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Constant.USER_TOKEN_KEY);
        if(token == null){
            return;
        } else {

        }

        if(!TokenUtil.isLogin()) {
//            throw BusinessException.fail(FrontRespEnum.LOGIN_INFO_EXIST);
        }
    }

    public static String[] excludePath() {
        return new String[]{
                "/article/all",
                "/article/[0-9]",
                "/balanceLog/*",
                "/userExtractWallet/*",
                "/packetRecord/*",
                "/withdrawCash/*",
                "/redPacket/*"
        };
    }

    /**
     * 是否需要检查用户的登录状态
     */
    private static boolean isInclude(String url) {
        for (String excludePath : excludePath()) {
            PathMatcher pathMatcher = new AntPathMatcher();
            if (pathMatcher.match(excludePath, url)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isInclude("/article/2"));
    }
}
