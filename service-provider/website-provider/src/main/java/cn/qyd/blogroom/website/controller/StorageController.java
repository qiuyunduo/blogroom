package cn.qyd.blogroom.website.controller;

import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.website.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * @Author qyd
 * @Date 19-3-12 下午5:06
 **/
@RestController
@RequestMapping("/storage")
@Api(tags = "图片上传")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    @ApiOperation(("上传图片"))
    public Resp create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String url = storageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
        return Resp.succeed(url);
    }
}
