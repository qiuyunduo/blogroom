package cn.qyd.blogroom.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author qyd
 * @Date 19-3-1 上午9:40
 **/
public class LoginUtil {

    public static Boolean isLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String url = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(url);

//        if (!isInclude(url)) {
//            String token = request.getHeader(Constant.USER_TOKEN_KEY);
//            if (StringUtils.isNotBlank(token)) {
//                token = token.replaceFirst(Constant.TOKEN_PREFIX, "");
//            }
//            if (StringUtils.isBlank(token)) {
//                token = "";
//            }
//            TokenVo vo = userTokenClient.validateToken(token, TokenTypeEnum.USER.getValue());
//            if (vo != null && vo.getUserId() != null) {
//                request.setAttribute("userId", vo.getUserId());
//                if (StringUtils.isNotBlank(vo.getRefreshToken())) {
//                    response.setHeader(Constant.REFRESH_TOKEN_KEY, vo.getRefreshToken());
//                }
//            }
//        }
        return false;
    }
}
