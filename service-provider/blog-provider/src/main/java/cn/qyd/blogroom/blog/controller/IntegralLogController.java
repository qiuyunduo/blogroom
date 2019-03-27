package cn.qyd.blogroom.blog.controller;

import cn.qyd.blogroom.blog.dto.IntegralLogQueryDto;
import cn.qyd.blogroom.blog.dto.IntegralLogDto;
import cn.qyd.blogroom.blog.entity.IntegralLog;
import cn.qyd.blogroom.blog.service.IntegralLogService;
import cn.qyd.blogroom.blog.vo.IntegralLogVo;
import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import cn.qyd.blogroom.common.utils.PagingUtil;
import cn.qyd.blogroom.common.utils.dozer.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Author qyd
 * @Date 19-2-27 下午12:30
 **/
@RestController
@RequestMapping("/integral/log")
@Api("博客积分变动日志")
public class IntegralLogController {
    @Autowired
    private IntegralLogService integralLogService;

    @PostMapping("/create")
    @ApiOperation("添加积分变动日志")
    public Resp create(@RequestBody IntegralLogDto integralLogDto) {
        IntegralLog result = integralLogService.save(integralLogDto);
        return Resp.succeed(result);
    }

    @GetMapping("/query")
    @ApiOperation("获取所有积分变动记录")
    public Resp findAll(IntegralLogQueryDto queryDto) {
        Page<IntegralLog> result = integralLogService.query(queryDto);
        PagingInfo pageInfo = PagingUtil.page(result);
        pageInfo.setItem(BeanMapper.mapList(pageInfo.getItem(), IntegralLogVo.class));
        return Resp.succeedPaging(pageInfo);
    }
}
