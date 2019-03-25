package cn.qyd.blogroom.user.controller;

import cn.qyd.blogroom.common.annotations.CheckLogin;
import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import cn.qyd.blogroom.user.dao.AttentionDao;
import cn.qyd.blogroom.user.dto.AttentionDto;
import cn.qyd.blogroom.user.dto.AttentionQueryDto;
import cn.qyd.blogroom.user.entity.Attention;
import cn.qyd.blogroom.user.service.AttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-26 下午12:31
 **/
@RestController
@RequestMapping("attention")
@Api(tags = "关注和粉丝模块")
public class AttentionController {

    @Autowired
    private AttentionService attentionService;

    @Autowired
    private AttentionDao attentionDao;

    @PostMapping("/save")
    @ApiOperation("用户添加关注")
    @CheckLogin
    public Resp create(AttentionDto dto) {
        Attention result = attentionService.save(dto);
        return Resp.succeed(result);
    }

    @GetMapping("/find")
    @ApiOperation("获取一条关注记录")
    @CheckLogin
    public Resp findOne(AttentionDto dto) {
        Attention result = attentionService.selectOne(dto);
        return Resp.succeed(result);
    }

    @PostMapping("/remove")
    @ApiOperation("用户移除关注")
    @CheckLogin
    public Resp removeAttention(AttentionDto dto) {
        attentionService.delete(dto);
        return Resp.succeed(true);
    }

    @GetMapping("/fans")
    @ApiOperation("分页获取某用户所有粉丝")
    public Resp fans(AttentionQueryDto dto) {
        PagingInfo result = attentionService.pageFans(dto);
        return Resp.succeedPaging(result);
    }

    @GetMapping("/attentions")
    @ApiOperation("分页获取用户所有关注")
    public Resp attentions(AttentionQueryDto dto) {
        PagingInfo result = attentionService.pageFollowers(dto);
        return Resp.succeedPaging(result);
    }

    @GetMapping("/attentions/all")
    @ApiOperation("获取用户所有关注")
    public Resp attentionsAll(Long user1Id) {
        List<Long> idsByUser1Id = attentionDao.findIdsByUser1Id(user1Id);

        return Resp.succeed(idsByUser1Id);
    }
}
