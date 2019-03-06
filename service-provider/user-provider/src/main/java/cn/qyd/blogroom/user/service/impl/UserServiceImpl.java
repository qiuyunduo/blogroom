package cn.qyd.blogroom.user.service.impl;

import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.Formater;
import cn.qyd.blogroom.common.utils.MD5Util;
import cn.qyd.blogroom.user.dao.UserDao;
import cn.qyd.blogroom.user.dto.UpdatePwdDto;
import cn.qyd.blogroom.user.dto.UserDto;
import cn.qyd.blogroom.user.dto.UserQueryDto;
import cn.qyd.blogroom.user.dto.UserUpdateInfoDto;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.service.UserService;
import cn.qyd.blogroom.user.service.UserTokenService;
import cn.qyd.blogroom.user.vo.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-18 下午5:51
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public User save(UserDto dto) {
        User user = new User();
        user.setName(dto.getName())
                .setPassword(MD5Util.getMD5(dto.getPassword()))
                .setEmail(dto.getEmail())
                .setStatus(0)
                .setAddTime(LocalDateTime.now());
        User result = userDao.save(user);
        return result;
    }

    @Override
    public LoginUser login(String name, String password) {
        User user = userDao.findByName(name);
        if(user != null){
            if(!user.getPassword().equals(MD5Util.getMD5(password))){
                throw BusinessException.fail(FrontRespEnum.LOGIN_PWD_ERROR);
            }
        }else {
            throw BusinessException.fail(FrontRespEnum.THE_USER_NOT_EXIST);
        }

        String token = userTokenService.createOrRefreshToken(user.getId());

        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setName(user.getName());
        loginUser.setHeadImage(user.getHeadImage());
        loginUser.setToken(token);

        return loginUser;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> BusinessException.fail(FrontRespEnum.THE_USER_NOT_EXIST));
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        return userDao.findByPhone(phone);
    }

    @Override
    public Page<User> query(UserQueryDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage()-1,dto.getLimit());
        UserQueryParam param = new UserQueryParam(dto);
        Page<User> userPage = userDao.findAll(param,pageable);
        return userPage;
    }

    @Override
    public Boolean updatePassword(UpdatePwdDto dto) {
        User user = findById(dto.getId());
        if(MD5Util.getMD5(dto.getOldPassword()).equals(user.getPassword())){
            user.setPassword(MD5Util.getMD5(dto.getNewPassword()));
            userDao.save(user);
        }else {
            throw BusinessException.fail(FrontRespEnum.THE_OLD_PASSWORD_ERROR);
        }
        return true;
    }

    @Override
    public Boolean resetPassword(Long id, String password) {
        User user = findById(id);
        user.setPassword(MD5Util.getMD5(password));
        userDao.save(user);
        return true;
    }

    @Override
    public Boolean update(UserUpdateInfoDto infoDto) {
        User user = findById(infoDto.getId());
        user.setSex(infoDto.getSex())
                .setBirthday(Formater.parseLocalDate(infoDto.getBirthday()))
                .setOccupation(infoDto.getOccupation())
                .setArea(infoDto.getArea())
                .setDescription(infoDto.getDescription())
                .setUpdateTime(LocalDateTime.now());
        userDao.save(user);
        return true;
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        User user = findById(id);
        user.setStatus(status);
        userDao.save(user);
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        userDao.deleteById(id);
        return true;
    }

    /**
     * 查询条件
     */
    class UserQueryParam implements Specification {
        UserQueryDto queryDto;
        public UserQueryParam(UserQueryDto dto ){
            this.queryDto=dto;
        }

        @Override
        public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<Predicate>();
            if(StringUtils.isNotEmpty(queryDto.getName())){
                list.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+queryDto.getName()+"%"));
            }
            if(queryDto.getStatus() != null){
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), queryDto.getStatus()));
            }

            Predicate[] p = new Predicate[list.size()];
            criteriaQuery.where(list.toArray(p));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("addTime").as(LocalDateTime.class)));
            return criteriaQuery.getRestriction();
        }
    }
}
