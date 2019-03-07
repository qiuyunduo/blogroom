package cn.qyd.blogroom.common.utils;

import cn.qyd.blogroom.common.constants.Constant;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.jwt.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author qyd
 * @Date 19-3-1 上午9:40
 **/
@Component
public class TokenUtil {

    @Autowired
    private ValueOperations valueOperations;

    public static Boolean isLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String url = request.getRequestURI();

        return false;
    }

    public String createOrRefreshToken(Long userId) {
        long now = System.currentTimeMillis();
        String token;
        token = JwtTokenUtil.generateUserToken(userId.toString(), Constant.TEST_TOKEN_REFRESH);
//        valueOperations.set(token,userId);
//        valueOperations.getOperations().expireAt(token,new Date(now+Constant.TEST_TOKEN_EXPIRATION));

        return token;
    }

    public Boolean isTokenInRedis(String token) {
        Object newToken = valueOperations.get(token);
        if(newToken != null) {
            return true;
        }
        return false;
    }

    public Long getUserIdFronRedis(String token) {
        Long userId = (Long)valueOperations.get(token);

        return userId;
    }

    public Boolean removeToken(String token) {
        valueOperations.getOperations().delete(token);
        return true;
    }

    public Boolean validateToken(String token) {
        Long userId;
        try {
            //通过获取token中数据触发token检查过期功能
            userId = JwtTokenUtil.getUserIdFromToken(token);
        }catch (ExpiredJwtException e) {
//            //token过期,判断redis中token是否过期，是：用户应重新登录　否：刷新token
//            if(isTokenInRedis(token)) {
//                if(token)
//                //redis中还存有用户token,只是传过来的token过期了，此时刷新token
//                String newToken = createOrRefreshToken(getUserIdFronRedis(token));
//                HeaderUtil.setRefreshToken(token);
//            } else {
                throw BusinessException.fail(FrontRespEnum.THE_USER_TOKEN_EXPIRE);
//            }
        }catch (Exception e) {
            throw  e;
        }
        return true;
    }
}
