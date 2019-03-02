package cn.qyd.blogroom.website.service;

import cn.qyd.blogroom.website.dto.AdminDto;
import cn.qyd.blogroom.website.entity.Admin;

public interface AdminService {
    Admin save(AdminDto dto);
    Admin login(String name, String password);
}
