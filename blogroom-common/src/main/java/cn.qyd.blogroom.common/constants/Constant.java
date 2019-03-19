package cn.qyd.blogroom.common.constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongyikai on 2018/1/31.
 */
public class Constant {

    public static final String REDIS_TOKEN_KEY_PREFIX = "loginUser_";
    // 用户Token key
    public static final String USER_TOKEN_KEY = "User_Access_Token";
    // 用户id
    public static final String USER_ID_KEY = "User_Id";
    // 刷新token
    public static final String REFRESH_TOKEN_KEY = "refresh_token";
    // token 前缀
    public static final String TOKEN_PREFIX = "Bearer ";


    public static final String RIPPLE_ERROR = "error";

    //定义线程池大小,默认为(CPU+1)*2
    public static int Thread_SIZE = (Runtime.getRuntime().availableProcessors() + 1) * 2;

    //设计一个线程池，方便执行线程任务
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ExecutorService executorService = new ThreadPoolExecutor(Thread_SIZE, Thread_SIZE, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

    public static final String LOCALE = "locale";
    public static final String EN_US = "en_US";
    public static final String ZH_CN = "zh_CN";

    // 验证码发送周期 1分钟
    public static final Long SEND_PERIOD = 60 * 1000L;
    // 验证码有效时间
    public static final Long CAPTCHA_EXPIRE = 10 * 60 * 1000L;
    // 验证码长度
    public static final Integer CAPTCHA_LENGTH = 6;

    // token过期时间 15天
    public static final Long TOKEN_EXPIRATION = 15 * 24 * 60 * 60 * 1000L;
    // token 刷新时间 7天
    public static final Long TOKEN_REFRESH = 7 * 24 * 60 * 60 * 1000L;
    //存储当前是否已经刷新token的过期时间 半分钟
    public static final Long REFRESH_TOKEN_EXPIRATION = 30 * 1000L;
    // 测试环境token过期时间 2分钟
    public static final Long TEST_TOKEN_EXPIRATION = 2 * 60 * 1000L;
    // 测试环境token 刷新时间 1分钟
    public static final Long TEST_TOKEN_REFRESH = 60 * 1000L;

}
