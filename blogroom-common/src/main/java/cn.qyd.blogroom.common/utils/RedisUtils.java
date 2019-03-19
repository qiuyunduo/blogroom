//package cn.qyd.blogroom.common.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//
//import java.util.Collections;
//
///**
// * @Author qyd
// * @Date 19-3-18 下午5:18
// **/
//@Component
//public class RedisUtils {
//
//
//
//
//
//    /**
//     * 尝试获取分布式锁
//     * @param lockKey 锁
//     * @param requestId 请求标识
//     * @param expireTime 超期时间
//     * @return 是否获取成功
//     */
//    public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
//        /**
//         * "NX" 意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
//         * "PX" ，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
//         */
//        jedis.auth("123456");
//        jedis.set("asd","asd");
//        String result = jedis.setn(lockKey, requestId, "NX", "PX", expireTime);
//
//        if (result.equals("OK")) {
//            return true;
//        }
//        return false;
//
//    }
//
//    /**
//     * 释放分布式锁
//     * @param lockKey 锁
//     * @param requestId 请求标识
//     * @return 是否释放成功
//     */
//    public static boolean releaseDistributedLock( String lockKey, String requestId) {
//        jedis.auth("123456");
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
//
//        if (result.equals(1L)) {
//            return true;
//        }
//        return false;
//
//    }
//}
