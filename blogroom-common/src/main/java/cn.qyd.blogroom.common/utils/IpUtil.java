package cn.qyd.blogroom.common.utils;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author qyd
 * @Date 19-1-14 下午3:55
 **/
public class IpUtil {
    /**
     * 获取http请求的真实IP地址
     * @return
     */
    public static String getIP() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request == null){
            return null;
        }
        //X-Forwarded-For：Squid 服务代理
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            //Proxy-Client-IP：apache 服务代理
            ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            //WL-Proxy-Client-IP：weblogic 服务代理
            ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            //HTTP_CLIENT_IP：有些代理服务器
            ip = request.getHeader("HTTP_CLIENT_IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //X-Real-IP：nginx服务代理
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getRemoteAddr();
        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ip != null && ip.length() != 0) {
            ip = ip.split(",")[0];
        }
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "0:0:0:0:0:0:0:1%0".equals(ip)){
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException unknownhostexception) {
            }

        }
        return ip;
    }
}
