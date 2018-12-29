package cn.qyd.blogroom.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 18-12-29 下午12:31
 **/
@RestController
public class UserController {

    @Value("${User}")
    String user;

    @Value("${spring.profiles.active}")
    String active;

    @GetMapping("/getUser")
    public String getUser(){
        return user+active;
    }
}
