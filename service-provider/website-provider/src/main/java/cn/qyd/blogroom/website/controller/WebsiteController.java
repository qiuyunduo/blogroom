package cn.qyd.blogroom.website.controller;

import cn.qyd.blogroom.client.user.UserClient;
import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.website.entity.FriendlyLink;
import cn.qyd.blogroom.website.service.FriendlyLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-22 上午11:40
 **/
@RestController
@RequestMapping()
@Api(tags = "网站相关模块")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebsiteController {
    private final UserClient userClient;
    private final FriendlyLinkService linkService;

    @GetMapping("/info")
    @ApiOperation("获取网站相关信息")
    public String siteInfo() {
        return "网站的基本信息";
    }

    @GetMapping("/friendly/links")
    @ApiOperation("获取所有友情链接")
    public Resp friendlyLinks() {
        List<FriendlyLink> friendlyLinks = linkService.allLinks();
        return Resp.succeed(friendlyLinks);
    }

}
