package cn.qyd.blogroom.common.utils;

import cn.qyd.blogroom.common.constants.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderUtil {
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
//    public static Long getUserId() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Object userId = request.getAttribute("userId");
//        if (userId != null) {
//            return Long.valueOf(userId.toString());
//        }
//        throw UserServiceException.create(UserErrorEnum.USER_NOT_LOGIN);
//    }

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

    /**
     * 获取当前系统
     */
    public static String getSystem() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader(Constant.APP_SYSTEM);
    }

//    public static SystemEnum getSystemEnum() {
//        SystemEnum systemEnum = SystemEnum.get(getSystem());
//        return systemEnum;
//    }

    /**
     * 获取当前APP版本
     */
    public static String getVersion() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader(Constant.APP_VERSION);
    }

    /**
     * 获取当前语言CODE
     */
    public static String getCurrentLanguage() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader(Constant.APP_LOCALE);
    }

//    public static LanguageEnum getLanguage() {
//        return LanguageEnum.get(getCurrentLanguage());
//    }
//
//    public static Integer getCurrentLanguageId() {
//        return getLanguage().getId();
//    }

}

