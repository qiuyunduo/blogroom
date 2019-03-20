package cn.qyd.blogroom.common.utils;

import cn.qyd.blogroom.common.constants.Constant;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.jwt.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @Author qyd
 * @Date 19-3-1 上午9:40
 **/
@Component
public class TokenUtil {
    @Autowired
    private ValueOperations valueOperations;

    public String createOrRefreshToken(Long userId) {
        String token;
        token = JwtTokenUtil.generateUserToken(userId.toString(), Constant.TOKEN_REFRESH);
        setExpireTime(userId,token);
        return token;
    }

    public void setExpireTime(Long userId, String token) {
        long now = System.currentTimeMillis();
        valueOperations.set(Constant.REDIS_TOKEN_KEY_PREFIX+userId,token);
        valueOperations.getOperations().expireAt(Constant.REDIS_TOKEN_KEY_PREFIX+userId,new Date(now+Constant.TOKEN_EXPIRATION));
    }

    public void setRefreshToken(String token, String refreshToken) {
        long now = System.currentTimeMillis();
        valueOperations.set(token,refreshToken);
        valueOperations.getOperations().expireAt(token,new Date(now+Constant.REFRESH_TOKEN_EXPIRATION));
    }

    public String getRefreshToken(String token) {
        Object refreshToken = valueOperations.get(token);
        if(refreshToken != null) {
            return String.valueOf(refreshToken);
        }
        return null;
    }

    public String  getTokenFromRedis(Long userId) {
        Object newToken = valueOperations.get(Constant.REDIS_TOKEN_KEY_PREFIX+userId.toString());
        if(newToken != null) {
            return String.valueOf(newToken);
        }
        throw BusinessException.fail(FrontRespEnum.LOGIN_INFO_EXIST);
    }

    public Boolean removeToken(Long userId) {
        valueOperations.getOperations().delete(Constant.REDIS_TOKEN_KEY_PREFIX+userId);
        return true;
    }

    public Boolean validateToken(String token) {
        Long userId;
        try {
            //通过获取token中数据触发token检查过期功能
            JwtTokenUtil.getExpirationDateFromToken(token);
        }catch (ExpiredJwtException e) {
            return false;
        }catch (Exception e) {
            throw  e;
        }
        return true;
    }
}
