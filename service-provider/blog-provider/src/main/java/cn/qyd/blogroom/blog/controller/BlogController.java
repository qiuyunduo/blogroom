package cn.qyd.blogroom.blog.controller;

import cn.qyd.blogroom.blog.dto.BlogDto;
import cn.qyd.blogroom.blog.entity.Blog;
import cn.qyd.blogroom.blog.service.BlogService;
import cn.qyd.blogroom.common.resp.Resp;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 19-2-21 下午6:12
 **/
@RestController
@RequestMapping
@Api(tags = "博客空间相关")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/create")
    public Resp blogInfo(BlogDto dto) {
        Blog blog = blogService.save(dto);
        return Resp.succeed(blog);
    }

    @GetMapping("/room/{userId}")
    public Resp blogInfo(@PathVariable("userId") Long userId) {
        Blog blog = blogService.findByUserId(userId);
        return Resp.succeed(blog);
    }
}
