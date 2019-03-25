package cn.qyd.blogroom.blog.controller;

import cn.qyd.blogroom.blog.dto.RankDto;
import cn.qyd.blogroom.blog.entity.Rank;
import cn.qyd.blogroom.blog.service.RankService;
import cn.qyd.blogroom.blog.vo.RankVo;
import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.common.utils.dozer.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        RankVo rankVo = BeanMapper.map(rank, RankVo.class);
        return Resp.succeed(rankVo);
    }

    @GetMapping("/all")
    @ApiOperation("获取所有博客等级信息")
    public Resp allRanks() {
        List<Rank> ranks = rankService.all();
        List<RankVo> rankVos = BeanMapper.mapList(ranks, RankVo.class);

        return Resp.succeed(rankVos);
    }

    @PostMapping("/update")
    @ApiOperation("获取所有博客等级信息")
    public Resp updateRank(@RequestBody RankDto dto) {
        Boolean result = rankService.update(dto);
        return Resp.succeed(result);
    }
}
