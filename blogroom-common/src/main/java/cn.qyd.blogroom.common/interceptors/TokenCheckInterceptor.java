package cn.qyd.blogroom.common.interceptors;

import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.HeaderUtil;
import cn.qyd.blogroom.common.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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

    @Autowired
    private JedisPool jedisPool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = HeaderUtil.getUserToken();
        Long userId;
        if(StringUtils.isEmpty(token)){
            //TODO 说明该请求为用户登录之前的请求
        } else {
            userId = HeaderUtil.getUserId();
            String redisToken = tokenUtil.getTokenFromRedis(userId);
            if(tokenUtil.validateToken(token)) {
                if(!StringUtils.equals(token,redisToken)){
                    throw BusinessException.fail(FrontRespEnum.OTHER_LOGIN);
                }
            } else {
                String refreshToken = getRefreshToken(userId,token);
                System.out.println(request.getRequestURL()+ " : " + refreshToken);
                HeaderUtil.setRefreshToken(refreshToken);
                tokenUtil.setExpireTime(userId,refreshToken);
            }
            //TODO 否则该请求为登录之后的请求，需验证token是否有效
            /**TODO 1.判断token是否过期，未过期，进入下一步，否则取出redis中存入的token,查看redis中token是否过期，是　请重新登录，　否刷新token
             *      2.利用一起传过来的userId取出存入redis中的token比对是否一致,下一步,否则抛出异常“您的账号在其他地方已被登录，请重新在此处登录”
             *      3.token没有问题，正常请求
             */

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    public String getRefreshToken(Long userId, String token) {
        String refreshToken = tokenUtil.createOrRefreshToken(userId);
        Jedis jedis = jedisPool.getResource();
        //setnx用于控制并发请求刷新token时只刷新一次
        if(jedis.setnx(token,refreshToken) == 0) {
            refreshToken = tokenUtil.getRefreshToken(token);
        } else {
            jedis.expire(token,20);
        }
        return refreshToken;
    }
}
