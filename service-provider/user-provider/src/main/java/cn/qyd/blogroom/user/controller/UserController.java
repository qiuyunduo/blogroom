package cn.qyd.blogroom.user.controller;

import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.user.dto.UserDto;
import cn.qyd.blogroom.user.dto.UserUpdateInfoDto;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.service.UserService;
import cn.qyd.blogroom.user.service.UserTokenService;
import cn.qyd.blogroom.user.vo.LoginUser;
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

    @Autowired
    private UserTokenService tokenService;

    @GetMapping("createToken")
    @ApiOperation("生成token，过期时间1分钟")
    public Resp createToken(Long userId) {
        String token = tokenService.createOrRefreshToken(userId);
        return Resp.succeed(token);
    }

    @GetMapping("getUserId")
    @ApiOperation("测试token过期时间是否自动检查，获得ｔｏｋｅｎ中userid")
    public Resp validateToken(String token) {
        Long userId = tokenService.validateToken(token);
        return Resp.succeed(userId);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Resp register(UserDto userDto){

        User user = userService.save(userDto);
        return Resp.succeed(user);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Resp login(String name, String password){
        LoginUser user = userService.login(name, password);
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
