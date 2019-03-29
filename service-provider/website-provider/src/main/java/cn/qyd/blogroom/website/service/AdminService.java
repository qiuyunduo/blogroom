package cn.qyd.blogroom.website.service;

import cn.qyd.blogroom.website.dto.AdminDto;
import cn.qyd.blogroom.website.dto.AdminUpdateDto;
import cn.qyd.blogroom.website.entity.Admin;
import java.util.List;

public interface AdminService {
    List<Admin> all();
    Admin save(AdminDto dto);
    Admin login(String name, String password);
    void loginOut(Long id);
    Admin update(AdminUpdateDto dto);
    Boolean delete(Long id);
}
