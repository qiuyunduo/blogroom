package cn.qyd.blogroom.blog.controller;
import cn.qyd.blogroom.blog.dto.BlogStatusDto;
import cn.qyd.blogroom.blog.entity.Blog;
import cn.qyd.blogroom.blog.service.BlogService;
import cn.qyd.blogroom.common.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    @ApiOperation("新增博客账号")
    public Resp addOne(Long userId) {
        Blog blog = blogService.save(userId);
        return Resp.succeed(blog);
    }

    @PostMapping("/update")
    @ApiOperation("新增博客账号")
    public Resp updateOne(@RequestBody BlogStatusDto statusDto) {
        Boolean result = blogService.updateBlogStatus(statusDto);
        return Resp.succeed(result);
    }

    @GetMapping("/room/{userId}")
    @ApiOperation("根据用户id获取该用户的博客账号")
    public Resp blogInfo(@PathVariable("userId") Long userId) {
        Blog blog = blogService.findByUserId(userId);
        return Resp.succeed(blog);
    }

    @GetMapping("/room/visit")
    @ApiOperation("访客浏览博客")
    public Resp visit(Long userId) {
        Boolean result = blogService.updateBlogVisit(userId);
        return Resp.succeed(result);
    }
}
