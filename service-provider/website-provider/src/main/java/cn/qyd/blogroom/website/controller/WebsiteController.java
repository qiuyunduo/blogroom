package cn.qyd.blogroom.website.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 19-2-22 上午11:40
 **/
@RestController
@RequestMapping("/info")
@Api("网站相关模块")
public class WebsiteController {

    @GetMapping
    @ApiOperation("获取网站相关信息")
    public String siteInfo() {
        return "网站的基本信息";
    }
}
