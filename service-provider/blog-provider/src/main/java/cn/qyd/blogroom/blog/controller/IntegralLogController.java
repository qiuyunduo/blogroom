package cn.qyd.blogroom.blog.controller;

import cn.qyd.blogroom.blog.entity.IntegralLog;
import cn.qyd.blogroom.blog.service.IntegralLogService;
import cn.qyd.blogroom.common.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Resp create(IntegralLog integralLog) {
        IntegralLog result = integralLogService.save(integralLog);
        return Resp.succeed(result);
    }
}
