package cn.qyd.blogroom.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:08
 */
@FeignClient(name = "user-provider")
public interface UserClient {

    @GetMapping("/info/getUser")
    String getUser();
}
