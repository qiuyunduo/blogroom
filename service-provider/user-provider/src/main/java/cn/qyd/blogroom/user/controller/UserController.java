package cn.qyd.blogroom.user.controller;

import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.user.dto.UserDto;
import cn.qyd.blogroom.user.dto.UserUpdateInfoDto;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author qyd
 * @Date 18-12-29 下午12:31
 **/
@RestController
@RequestMapping
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Resp register(UserDto userDto){
        User user = userService.save(userDto);
        return Resp.succeed(user);
    }

    @GetMapping("/login")
    @ApiOperation("用户登录")
    public Resp login(String name, String password){
        User user = userService.login(name, password);
        return Resp.succeed(user);
    }

    @PutMapping("/updateInfo")
    @ApiOperation("修改用户基本信息")
    public Resp updateBaseInfo(UserUpdateInfoDto infoDto) {
        Boolean result = userService.update(infoDto);
        return Resp.succeed(result);
    }

    @GetMapping("/client/user/{id}")
    @ApiOperation("根据用户id获取用户信息")
    public User findOne(@PathVariable("id")Long id){
        return userService.findById(id);
    }
}
