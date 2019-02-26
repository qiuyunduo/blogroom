package cn.qyd.blogroom.user.controller;

import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import cn.qyd.blogroom.common.utils.PagingUtil;
import cn.qyd.blogroom.common.utils.dozer.BeanMapper;
import cn.qyd.blogroom.user.dto.AttentionQueryDto;
import cn.qyd.blogroom.user.entity.Attention;
import cn.qyd.blogroom.user.service.AttentionService;
import cn.qyd.blogroom.user.vo.AttentionVo;
import cn.qyd.blogroom.user.vo.FanVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 19-2-26 下午12:31
 **/
@RestController
@RequestMapping
@Api(tags = "关注和粉丝模块")
public class AttentionController {

    @Autowired
    private AttentionService attentionService;

    @GetMapping("/fans")
    @ApiOperation("获取某用户所有粉丝")
    public Resp fans(AttentionQueryDto dto) {
        Page<Attention> resultPage = attentionService.query(dto);
        PagingInfo pageInfo = PagingUtil.page(resultPage);
        pageInfo.setItem(BeanMapper.mapList(pageInfo.getItem(), FanVo.class));
        return Resp.succeedPaging(pageInfo);
    }

    @GetMapping("/attentions")
    @ApiOperation("获取用户所有关注")
    public Resp attentions(AttentionQueryDto dto) {
        Page<Attention> resultPage = attentionService.query(dto);
        PagingInfo pageInfo = PagingUtil.page(resultPage);
        pageInfo.setItem(BeanMapper.mapList(pageInfo.getItem(), AttentionVo.class));
        return Resp.succeedPaging(pageInfo);
    }
}
