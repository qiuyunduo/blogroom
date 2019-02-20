package cn.qyd.blogroom.user.controller;

import cn.qyd.blogroom.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 18-12-29 下午12:31
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${User}")
    String user;

    @Value("${spring.profiles.active}")
    String active;

    @Value("${testurl}")
    String url;

    @GetMapping("/getUser")
    @ApiOperation("测试用例")
    public String getUser(){
        return user+"   "+active+"   "+url;
    }

    @GetMapping("/user/all")
    @ApiOperation("获取所有用户")
    public Object getAll(){
        return userService.selectAll();
    }
}
