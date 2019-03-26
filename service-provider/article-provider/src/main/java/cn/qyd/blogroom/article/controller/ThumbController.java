package cn.qyd.blogroom.article.controller;

import cn.qyd.blogroom.article.dto.ThumbDto;
import cn.qyd.blogroom.article.entity.Thumb;
import cn.qyd.blogroom.article.service.ThumbService;
import cn.qyd.blogroom.common.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Resp addThumb(@RequestBody ThumbDto dto) {
        Thumb result = thumbService.save(dto);
        return Resp.succeed(result);
    }

    @GetMapping("/find")
    @ApiOperation("根据文章id和用户id查找是否点赞")
    public Resp findThumb(Long articleId, Long userId) {
        Thumb thumb = thumbService.findOne(articleId,userId);
        return Resp.succeed(thumb);
    }

    @PostMapping("/remove")
    @ApiOperation("取消点赞")
    public Resp delThumb(Long id, Long articleId) {
        thumbService.delete(id,articleId);
        return Resp.succeed(true);
    }

    @GetMapping("/count")
    @ApiOperation("某博主获得的总赞数")
    public Resp countThumb(Long userId) {
        Long count = thumbService.countThumbsOfUser(userId);
        return Resp.succeed(count);
    }
}
