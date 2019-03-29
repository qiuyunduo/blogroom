package cn.qyd.blogroom.website.service.impl;

import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.IpUtil;
import cn.qyd.blogroom.common.utils.MD5Util;
import cn.qyd.blogroom.website.dao.AdminDao;
import cn.qyd.blogroom.website.dto.AdminDto;
import cn.qyd.blogroom.website.dto.AdminUpdateDto;
import cn.qyd.blogroom.website.entity.Admin;
import cn.qyd.blogroom.website.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-27 下午2:13
 **/
@Service
public class AdminServiceImpl implements AdminService
{
    @Autowired
    private AdminDao adminDao;

    @Override
    public List<Admin> all() {
        return adminDao.findAll();
    }

    @Override
    public Admin save(AdminDto dto) {
        Admin admin = new Admin();
        admin.setName(dto.getName())
                .setPassword(MD5Util.getMD5(dto.getPassword()))
                .setEmail(dto.getEmail())
                .setStatus(dto.getStatus())
                .setLoginNumber(0)
                .setAddTime(LocalDateTime.now());
        Admin result = adminDao.save(admin);
        return result;
    }

    @Override
    @Transactional
    public Admin login(String name, String password) {
        Admin admin = adminDao.findByName(name);
        if(admin != null) {
//            if(admin.getStatus() == 1) {
//                throw BusinessException.fail(FrontRespEnum.ADMIN_LOCK);
//            }
//            if(admin.getStatus() == 2) {
//                throw BusinessException.fail(FrontRespEnum.ADMIN_HAVING_DELETE);
//            }
//            if(!MD5Util.getMD5(password).equals(admin.getPassword())){
//                throw BusinessException.fail(FrontRespEnum.ADMIN_LOGIN_FAIL_PASSWORD_ERROR);
//            }
            if(admin.getLastLoginIp() == null) {
                admin.setLastLoginIp(IpUtil.getIP())
                        .setLastLoginTime(LocalDateTime.now());
            }
            admin.setLoginNumber(admin.getLoginNumber() + 1);

            adminDao.save(admin);
        } else {
            throw BusinessException.fail(FrontRespEnum.ADMIN_NOT_EXIST);
        }

        return admin;
    }

    @Override
    public void loginOut(Long id) {
        Admin admin = adminDao.findById(id).get();
        admin.setLastLoginIp(IpUtil.getIP())
                .setLastLoginTime(LocalDateTime.now());
        adminDao.save(admin);
    }

    @Override
    public Admin update(AdminUpdateDto dto) {
        Admin admin = adminDao.findById(dto.getId()).get();
        admin.setStatus(dto.getStatus());
        Admin result = adminDao.save(admin);
        return result;
    }

    @Override
    public Boolean delete(Long id) {
        adminDao.deleteById(id);
        return true;
    }
}
