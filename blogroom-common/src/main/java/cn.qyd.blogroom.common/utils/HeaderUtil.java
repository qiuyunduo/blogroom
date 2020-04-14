package cn.qyd.blogroom.common.utils;

import cn.qyd.blogroom.common.constants.Constant;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class HeaderUtil {

    /**
     * 获取Admin_Login
     */
    public static String getAdminLogin() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String isLoign = request.getHeader(Constant.ADMIN_IS_LOGIN);
        if (StringUtils.isNotBlank(isLoign)) {
            return isLoign;
        }
        return null;
    }
    /**
     * 获取Token
     */
    public static String getUserToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(Constant.USER_TOKEN_KEY);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        return null;
    }

    public static void setRefreshToken(String token) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader(Constant.REFRESH_TOKEN_KEY,token);
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String userId = request.getHeader(Constant.USER_ID_KEY);
        if (StringUtils.isNotBlank(userId)) {
            return Long.valueOf(userId);
        }
        throw BusinessException.fail(FrontRespEnum.LOGIN_INFO_EXIST);
    }

    /**
     * 获取用户id，没有则返回null
     */
    public static Long getUserIdOrNull() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object userId = request.getAttribute("userId");
        if (userId != null) {
            return Long.valueOf(userId.toString());
        }
        return null;
    }

}

