package cn.qyd.blogroom.user.service.impl;

import cn.qyd.blogroom.common.constants.Constant;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.jwt.JwtTokenUtil;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.service.UserTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author qyd
 * @Date 19-3-6 上午11:02
 **/
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private ValueOperations valueOperations;

    @Override
    public String createOrRefreshToken(Long userId) {
        long now = System.currentTimeMillis();
        String token;
        token = JwtTokenUtil.generateUserToken(userId.toString(), Constant.TEST_TOKEN_REFRESH);
        valueOperations.set("loginUser."+userId,token);
        valueOperations.getOperations().expireAt("loginUser"+userId,new Date(now+Constant.TEST_TOKEN_EXPIRATION));
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader(Constant.REFRESH_TOKEN_KEY, token);

        return token;
    }

    @Override
    public Long validateToken(String token) {
//        if(JwtTokenUtil.getExpirationDateFromToken(token) == null){
//
//        }
        Long userId;
        try {
            userId = JwtTokenUtil.getUserIdFromToken(token);
        }catch (ExpiredJwtException e) {
            throw BusinessException.fail(FrontRespEnum.THE_USER_TOKEN_EXPIRE);
        }catch (Exception e) {
            throw BusinessException.fail(FrontRespEnum.THE_USER_TOKEN_EXPIRE);
        }
        return userId;
    }
}
