package cn.qyd.blogroom.user.controller;

import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import cn.qyd.blogroom.common.utils.CaptchaUtil;
import cn.qyd.blogroom.common.utils.PagingUtil;
import cn.qyd.blogroom.common.utils.TokenUtil;
import cn.qyd.blogroom.common.utils.ToolUtil;
import cn.qyd.blogroom.common.utils.dozer.BeanMapper;
import cn.qyd.blogroom.user.dto.*;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.enums.TypeEnum;
import cn.qyd.blogroom.user.service.UserService;
import cn.qyd.blogroom.user.vo.LoginUser;
import cn.qyd.blogroom.user.vo.SimpleUserVo;
import cn.qyd.blogroom.user.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author qyd
 * @Date 18-12-29 下午12:31
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/fashion")
    @ApiOperation("获取时尚博主")
    public Resp fashionUser() {
        List<User> users = userService.fashionUser();
        List<SimpleUserVo> simpleUserVos = BeanMapper.mapList(users, SimpleUserVo.class);
        return Resp.succeed(simpleUserVos);
    }

    @PostMapping("/create")
    @ApiOperation("添加用户")
    public Resp addOne(UserDto userDto){
        User user = userService.save(userDto);
        return Resp.succeed(user);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Resp register(UserDto userDto){
        LoginUser loginUser = userService.register(userDto);
        return Resp.succeed(loginUser);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Resp login(String account, String password){
        LoginUser user = userService.login(account, password);
        return Resp.succeed(user);
    }

    @PostMapping("/logout")
    @ApiOperation("用户登出")
    public Resp logout(Long userId){
        Boolean result = userService.logout(userId);
        return Resp.succeed(result);
    }

    @PostMapping("/updateInfo")
    @ApiOperation("修改用户基本信息")
    public Resp updateBaseInfo(UserUpdateInfoDto infoDto) {
        Boolean result = userService.update(infoDto);
        return Resp.succeed(result);
    }

    @PostMapping("/updatePwd")
    @ApiOperation("修改用户密码")
    public Resp updatePwd(UpdatePwdDto dto) {
        Boolean result = userService.updatePassword(dto);
        return Resp.succeed(result);
    }

    @PostMapping("/updateHeadImage")
    @ApiOperation("修改用户头像")
    public Resp updateHeadImage(Long userId, String newImage) {
        Boolean result = userService.updateImage(userId, newImage);
        return Resp.succeed(result);
    }

    @GetMapping("/client/user/{id}")
    @ApiOperation("根据用户id获取用户信息")
    public User findOne(@PathVariable("id")Long id){
        return userService.findById(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据用户id获取用户信息")
    public Resp selectOne(@PathVariable("id")Long id){
        User user = userService.findById(id);
        UserVo userVo = BeanMapper.map(user, UserVo.class);
        return Resp.succeed(userVo);
    }

    @GetMapping("/simple/{id}")
    @ApiOperation("根据用户id获取用户昵称和头像")
    public Resp selectSimple(@PathVariable("id")Long id){
        User user = userService.findById(id);
        SimpleUserVo simpleUserVo = BeanMapper.map(user, SimpleUserVo.class);
        return Resp.succeed(simpleUserVo);
    }

    @GetMapping("/query")
    @ApiOperation("根据条件查询所有用户，管理员后台用到")
    public Resp query(UserQueryDto queryDto) {
        Page<User> resultPage = userService.query(queryDto);
        PagingInfo pageInfo = PagingUtil.page(resultPage);
        pageInfo.setItem(BeanMapper.mapList(pageInfo.getItem(),UserVo.class));
        return Resp.succeedPaging(pageInfo);
    }

    @PostMapping("/updateStatus")
    @ApiOperation("更新用户状态")
    public Resp updateStatus(@RequestBody UserUpdateStatusDto dto) {
        Boolean result = userService.updateStatus(dto);
        return Resp.succeed(result);
    }

    @GetMapping("/countAll")
    @ApiOperation("获取网站所有用户数量")
    public Resp countAll() {
        Long result = userService.countAll();
        return Resp.succeed(result);
    }

    @GetMapping("/validateCode")
    @ApiOperation("发送邮箱验证码")
    public Resp sendRegisterEmialCode(String email,Integer type) {
        userService.sendValidateCode(email,type);
        return Resp.succeed();
    }

    @GetMapping("/validateCode/resetPassword")
    @ApiOperation("重置密码邮箱验证码")
    public Resp sendResetPwdEmialCode() {
        String randomNumberString = ToolUtil.getRandomNumberString(6);
        CaptchaUtil.sendEmail("qiuyunduo@m-chain.com",randomNumberString);
        return Resp.succeed();
    }
}
