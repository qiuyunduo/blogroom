package cn.qyd.blogroom.common.utils.jwt;

/**
 * jwt相关配置
 *
 * @author fengshuonan
 * @date 2017-08-23 9:23
 */
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "defaultSecret";

    //JWT过期时间 7 天
//    private Long expiration = 7 * 24 * 60 * 60 * 1000L;
    //JWT过期时间 1分钟
    private Long expiration = 60 * 1000L;

    private String authPath = "auth";

    private String md5Key = "randomKey";

    public static String getJwtPrefix() {
        return JWT_PREFIX;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getAuthPath() {
        return authPath;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }
}
