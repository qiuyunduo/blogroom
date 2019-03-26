package cn.qyd.blogroom.blog.controller;

import cn.qyd.blogroom.blog.dto.IntegralConfigDto;
import cn.qyd.blogroom.blog.entity.IntegralConfig;
import cn.qyd.blogroom.blog.service.IntegralCongfigService;
import cn.qyd.blogroom.common.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author qyd
 * @Date 19-3-25 下午4:49
 **/
@RestController
@RequestMapping("/integral/config")
@Api("博客积分配置")
public class IntegralConfigController {
    @Autowired
    private IntegralCongfigService congfigService;

    @GetMapping("/find")
    @ApiOperation("获取积分配置")
    public Resp getConfig(Integer type) {
        IntegralConfig integralConfig = congfigService.findByType(type);
        return Resp.succeed(integralConfig);
    }

    @PostMapping("/edit")
    @ApiOperation("编辑积分配置")
    public Resp editConfig(@RequestBody  IntegralConfigDto dto) {
        Boolean result = congfigService.update(dto);
        return Resp.succeed(result);
    }
}
