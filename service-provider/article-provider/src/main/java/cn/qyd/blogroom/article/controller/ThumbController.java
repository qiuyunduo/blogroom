package cn.qyd.blogroom.article.controller;

import cn.qyd.blogroom.article.service.ThumbService;
import cn.qyd.blogroom.common.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public Resp addThumb(Long articleId, Long userId) {
        Boolean save = thumbService.save(articleId, userId);
        return Resp.succeed(save);
    }

    @PostMapping("/remove")
    @ApiOperation("取消点赞")
    public Resp delThumb(Long articleId, Long userId) {
        thumbService.delete(userId, articleId);
        return Resp.succeed(true);
    }

    @GetMapping("/count")
    @ApiOperation("某博主获得的总赞数")
    public Resp countThumb(Long userId) {
        Long count = thumbService.countThumbsOfUser(userId);
        return Resp.succeed(count);
    }
}
