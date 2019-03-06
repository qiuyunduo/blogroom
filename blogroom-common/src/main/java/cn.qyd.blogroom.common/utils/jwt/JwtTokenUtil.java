package cn.qyd.blogroom.common.utils.jwt;

import cn.qyd.blogroom.common.utils.ToolUtil;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>jwt token工具类</p>
 * <pre>
 *     jwt的claim里一般包含以下几种数据:
 *         1. iss -- token的发行者
 *         2. sub -- 该JWT所面向的用户
 *         3. aud -- 接收该JWT的一方
 *         4. exp -- token的失效时间
 *         5. nbf -- 在此时间段之前,不会被处理
 *         6. iat -- jwt发布时间
 *         7. jti -- jwt唯一标识,防止重复使用
 * </pre>
 *
 * @author fengshuonan
 */
public class JwtTokenUtil {

    private static JwtProperties jwtProperties = new JwtProperties();

    /**
     * 获取混淆MD5签名用的随机字符串
     */
    public static String getRandomKey() {
        return ToolUtil.getRandomString(6);
    }

    /**
     * 获取用户名从token中
     */
    public static Long getUserIdFromToken(String token) {
        String userId = getClaimFromToken(token).getSubject();

        return Long.valueOf(userId);
    }

    /**
     * 获取jwt接收者
     */
    public static String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取私有的jwt claim
     */
    public static String getPrivateClaimFromToken(String token, String key) {
        Object sub = getClaimFromToken(token).get(key);
        if (sub != null) {
            return sub.toString();
        }
        return null;
    }

    /**
     * 获取md5 key从token中
     */
    public static String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, jwtProperties.getMd5Key());
    }

    /**
     * 获取jwt的payload部分
     */
    public static Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 生成token(通过用户名和签名时候用的随机数)
     */
    public static String generateToken(String userName, String randomKey) {
        final Date createdDate = new Date();
        Map<String, Object> claims = new HashMap<>();
        claims.put(jwtProperties.getMd5Key(), randomKey);
        return doGenerateToken(claims, userName, false, null);
    }

    /**
     * 生成用户token
     */
    public static String generateUserToken(String userId, Long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(jwtProperties.getMd5Key(), getRandomKey());
        return doGenerateToken(claims, userId, true, expiration);
    }

    /**
     * 生成token
     *
     * @param isExpiration 是否会过期
     */
    private static String doGenerateToken(Map<String, Object> claims, String subject, boolean isExpiration, Long expiration) {
        final Date createdDate = new Date();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate);
        if (isExpiration) {
        	final Date expirationDate = new Date(System.currentTimeMillis() + expiration);
            builder.setExpiration(expirationDate);
        }
        return builder.signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public static void main(String[] args) {
//        String token =
//                "eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJtaWthMDUiLCJzdWIiOiIxIiwidHlwZSI6MiwiZXhwIjoxNTQyMDc5OTgyLCJpYXQiOjE1NDIwNzk5ODJ9.VbKKZC7iqMFOdWXAbpCtewe8tnCoyPMJciukdPxFk-k8KHRhmENpG0gdPqLNlmd1JHEVjV7ivmcpQ7LtlzMzIw";
    }
}