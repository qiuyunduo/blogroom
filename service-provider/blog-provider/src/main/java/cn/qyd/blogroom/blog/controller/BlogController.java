package cn.qyd.blogroom.blog.controller;

import cn.qyd.blogroom.blog.dto.BlogDto;
import cn.qyd.blogroom.blog.entity.Blog;
import cn.qyd.blogroom.blog.service.BlogService;
import cn.qyd.blogroom.common.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/blog")
@Api(tags = "博客空间相关")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/create")
    @ApiOperation("新增博客账号")
    public Resp blogInfo(BlogDto dto) {
        Blog blog = blogService.save(dto);
        return Resp.succeed(blog);
    }

    @GetMapping("/room/{userId}")
    @ApiOperation("根据用户id获取该用户的博客账号")
    public Resp blogInfo(@PathVariable("userId") Long userId) {
        Blog blog = blogService.findByUserId(userId);
        return Resp.succeed(blog);
    }
}
