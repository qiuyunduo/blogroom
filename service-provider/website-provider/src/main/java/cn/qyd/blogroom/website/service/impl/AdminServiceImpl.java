package cn.qyd.blogroom.website.service.impl;

import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.MD5Util;
import cn.qyd.blogroom.website.dao.AdminDao;
import cn.qyd.blogroom.website.dto.AdminDto;
import cn.qyd.blogroom.website.entity.Admin;
import cn.qyd.blogroom.website.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 19-2-27 下午2:13
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin save(AdminDto dto) {
        Admin admin = new Admin();
        admin.setName(dto.getName())
                .setPassword(MD5Util.getMD5(dto.getPassword()))
                .setEmail(dto.getEmail())
                .setStatus(0)
                .setAddTime(LocalDateTime.now());
        Admin result = adminDao.save(admin);
        return result;
    }

    @Override
    public Admin login(String name, String password) {
        Admin admin = adminDao.findByName(name);
        if(admin != null) {
            if(admin.getStatus() == 1) {
                throw BusinessException.fail(FrontRespEnum.ADMIN_LOCK);
            }
            if(admin.getStatus() == 2) {
                throw BusinessException.fail(FrontRespEnum.ADMIN_HAVING_DELETE);
            }
            if(!MD5Util.getMD5(password).equals(admin.getPassword())){
                throw BusinessException.fail(FrontRespEnum.ADMIN_LOGIN_FAIL_PASSWORD_ERROR);
            }
        } else {
            throw BusinessException.fail(FrontRespEnum.ADMIN_NOT_EXIST);
        }

        return admin;
    }
}
