package cn.qyd.blogroom.website.controller;

import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.website.dto.FriendlyLinkDto;
import cn.qyd.blogroom.website.entity.FriendlyLink;
import cn.qyd.blogroom.website.service.FriendlyLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-22 上午11:40
 **/
@RestController
@RequestMapping("/friendly/links")
@Api(tags = "网站友情链接模块")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FriendlyLinkController {
    private final FriendlyLinkService linkService;

    @GetMapping("/all")
    @ApiOperation("获取所有友情链接")
    public Resp friendlyLinks() {
        List<FriendlyLink> friendlyLinks = linkService.allLinks();
        return Resp.succeed(friendlyLinks);
    }

    @GetMapping("/countAll")
    @ApiOperation("获取所有友链数量")
    public Resp countAll() {
        Long result = linkService.countAll();
        return Resp.succeed(result);
    }

    @PostMapping("/create")
    @ApiOperation("获取所有友情链接")
    public Resp createLink(@RequestBody FriendlyLinkDto dto) {
        FriendlyLink friendlyLink = linkService.addOne(dto);
        return Resp.succeed(friendlyLink);
    }

    @PostMapping("/update")
    @ApiOperation("获取所有友情链接")
    public Resp updateLink(@RequestBody FriendlyLinkDto dto) {
        Boolean result = linkService.updateOne(dto);
        return Resp.succeed(result);
    }

    @PostMapping("/remove/{linkId}")
    @ApiOperation("删除友情链接")
    public Resp deleteLink(@PathVariable("linkId")Long linkId) {
        Boolean result = linkService.delOne(linkId);
        return Resp.succeed(result);
    }
}
