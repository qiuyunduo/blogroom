package cn.qyd.blogroom.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 19-2-21 下午6:12
 **/
@RestController
@RequestMapping("/blog")
@Api(tags = "博客空间相关")
public class BlogController {
    @GetMapping("/info")
    public String all() {
        return "该用户的博客信息";
    }
}
