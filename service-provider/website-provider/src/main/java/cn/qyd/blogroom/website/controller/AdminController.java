package cn.qyd.blogroom.website.controller;

import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.website.dto.AdminDto;
import cn.qyd.blogroom.website.dto.AdminUpdateDto;
import cn.qyd.blogroom.website.entity.Admin;
import cn.qyd.blogroom.website.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author qyd
 * @Date 19-2-27 下午1:33
 **/
@RestController
@RequestMapping("admin")
@Api(tags = "管理员模块")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final AdminService adminService;

    @GetMapping("all")
    @ApiOperation("获得所有管理员")
    public Resp queryAll() {
        return Resp.succeed(adminService.all());
    }

    @PostMapping("/create")
    @ApiOperation("添加管理员")
    public Resp create(@RequestBody AdminDto dto) {
        Admin admin = adminService.save(dto);
        return Resp.succeed(admin);
    }

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Resp login(String name,String password) {
        Admin loginUser = adminService.login(name, password);
        return Resp.succeed(loginUser);
    }

    @PostMapping("/loginOut")
    @ApiOperation("管理员登出")
    public Resp loginOut(Long id) {
        adminService.loginOut(id);
        return Resp.succeed();
    }

    @PostMapping("updateAdmin")
    @ApiOperation("更新管理员信息")
    public Resp updateAdmin(@RequestBody AdminUpdateDto dto) {
        Admin update = adminService.update(dto);
        return Resp.succeed(update);
    }

    @PostMapping("/delete")
    @ApiOperation("删除管理员")
    public Resp destoryAdmin(Long id) {
        adminService.delete(id);
        return Resp.succeed();
    }

}
