package cn.qyd.blogroom.front.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 19-2-19 下午4:35
 **/
@RestController
public class FrontTestController {

    @GetMapping("/getStr")
    public String getStr() {
        return "你瞅啥没再抽一下试试";
    }
}
