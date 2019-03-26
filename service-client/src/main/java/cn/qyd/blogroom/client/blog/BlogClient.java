package cn.qyd.blogroom.client.blog;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author qyd
 * @Date 19-3-26 下午5:04
 **/
@FeignClient(name = "blog-provider")
public interface BlogClient {

    @PostMapping("/client/blog/update")
    void update();
}
