package cn.qyd.blogroom.website.controller;

import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.website.dto.AdminDto;
import cn.qyd.blogroom.website.entity.Admin;
import cn.qyd.blogroom.website.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/create")
    @ApiOperation("添加管理员")
    public Resp create(AdminDto dto) {
        Admin admin = adminService.save(dto);
        return Resp.succeed(admin);
    }

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Resp login(String name,String password) {
        Admin loginUser = adminService.login(name, password);
        return Resp.succeed(loginUser);
    }

}
