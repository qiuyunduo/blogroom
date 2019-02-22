package cn.qyd.blogroom.article.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 19-2-21 下午6:09
 **/
@RestController
@RequestMapping("/article")
@Api(tags = "文章模块相关")
public class ArticleController {
    @GetMapping("/all")
    public String all() {
        return "所有文章";
    }
}
