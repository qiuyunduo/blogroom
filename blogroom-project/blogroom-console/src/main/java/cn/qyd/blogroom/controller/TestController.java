package cn.qyd.blogroom.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 18-12-29 上午11:56
 **/
@RestController
public class TestController {

    @Value("${author.name}")
    String name;

    public String getAuthor(){
        return name;
    }
}
