package cn.qyd.blogroom.blog.controller;

import cn.qyd.blogroom.blog.entity.Rank;
import cn.qyd.blogroom.blog.service.RankService;
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
 * @Date 19-3-11 上午11:53
 **/
@RestController
@RequestMapping("rank")
@Api("博客等级模块")
public class RankController {
    @Autowired
    private RankService rankService;

    @GetMapping("/{rankId}")
    @ApiOperation("根据博客等级id获取等级信息")
    public Resp rankDetail(@PathVariable("rankId")Long rankId) {
        Rank rank = rankService.findById(rankId);
        return Resp.succeed(rank);
    }
}
