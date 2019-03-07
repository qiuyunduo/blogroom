package cn.qyd.blogroom.common.interceptors;

import cn.qyd.blogroom.common.utils.HeaderUtil;
import cn.qyd.blogroom.common.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author qyd
 * @Date 19-3-7 下午2:31
 **/
@Component
public class TokenCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = HeaderUtil.getUserToken();
        Boolean userId = tokenUtil.validateToken(token);
        request.setAttribute("userId", userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    public String[] path() {
        return new String[]{
                "/user/login",
                "/logout",
                "/password/**",
                "/mobileOrEmail/**",
                "/thirdParty/**",
                "/userInfo/**"
        };
    }

    public String[] excludePath() {
        return new String[]{
                "/user/login"
        };
    }
}
