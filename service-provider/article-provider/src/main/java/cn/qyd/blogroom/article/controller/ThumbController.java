package cn.qyd.blogroom.article.controller;

import cn.qyd.blogroom.article.service.ThumbService;
import cn.qyd.blogroom.common.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 19-3-19 上午11:30
 **/
@RestController
@RequestMapping("thumb")
@Api(tags = "文章点赞模块")
public class ThumbController {
    @Autowired
    private ThumbService thumbService;

    @PostMapping("/save")
    @ApiOperation("用户点赞")
    public Resp addComment(Long articleId, Long userId) {
        Boolean save = thumbService.save(articleId, userId);
        return Resp.succeed(save);
    }
}
