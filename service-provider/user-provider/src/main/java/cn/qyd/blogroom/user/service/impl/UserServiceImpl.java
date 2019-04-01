package cn.qyd.blogroom.user.service.impl;

import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import cn.qyd.blogroom.common.utils.*;
import cn.qyd.blogroom.user.dao.UserDao;
import cn.qyd.blogroom.user.dto.*;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.enums.TypeEnum;
import cn.qyd.blogroom.user.service.UserService;
import cn.qyd.blogroom.user.vo.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired(required = false)
    private TokenUtil tokenUtil;

    @Value("${user.default.headImage}")
    private String defaultImage;

    @Override
    public User save(UserDto dto) {
        User user = new User();
        user.setAccount(dto.getAccount())
                .setNickName(dto.getNickName())
                .setPassword(MD5Util.getMD5(dto.getPassword()))
                .setEmail(dto.getEmail())
                .setHeadImage(defaultImage)
                .setStatus(0)
                .setAddTime(LocalDateTime.now());
        User result = userDao.save(user);
        if(result.getLastLoginIp() == null) {
            result.setLastLoginIp(IpUtil.getIP());
        }
        return result;
    }

    @Override
    public LoginUser login(String account, String password) {
        User user = userDao.findByAccount(account);
        if(user != null){
            if(user.getStatus() == 1) {
                throw BusinessException.fail(FrontRespEnum.USER_ACCOUNT_LOCK);
            }
            if(!user.getPassword().equals(MD5Util.getMD5(password))){
                throw BusinessException.fail(FrontRespEnum.LOGIN_PWD_ERROR);
            }
        }else {
            throw BusinessException.fail(FrontRespEnum.THE_USER_NOT_EXIST);
        }

        String token = tokenUtil.createOrRefreshToken(user.getId());

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setToken(token);

        return loginUser;
    }

    @Override
    public LoginUser register(UserDto dto) {
        if (userDao.findByAccount(dto.getAccount()) != null) {
            throw BusinessException.fail(FrontRespEnum.ACCOUNT_EXIST);
        }
        if (userDao.findByEmail(dto.getEmail()) != null){
            throw BusinessException.fail(FrontRespEnum.EMAIL_EXIST);
        }
        User user = save(dto);
        String token = tokenUtil.createOrRefreshToken(user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setToken(token);
        return loginUser;
    }

    @Override
    public Boolean logout(Long userId) {
        User user = findById(userId);
        userDao.save(user.setLastLoginIp(IpUtil.getIP()));
        Boolean result = tokenUtil.removeToken(userId);
        return result;
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
    public List<User> fashionUser() {
        Pageable pageable = PageRequest.of(0,10);
        UserQueryDto queryDto = new UserQueryDto();
        queryDto.setStatus(0);
        UserQueryParam param = new UserQueryParam(queryDto);
        Page<User> page = userDao.findAll(param,pageable);
        return page.getContent();
    }

    @Override
    public Long countAll() {
        return userDao.count();
    }

    @Override
    public Boolean updateImage(Long userId, String newImage) {
        User user = findById(userId);
        user.setHeadImage(newImage);
        userDao.save(user);
        return true;
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
    public Boolean sendValidateCode(String email, Integer type) {
        String code = ToolUtil.getRandomNumberString(6);
        String content = TypeEnum.getDescByCode(type) + code;
        if(type == 1) {
            if(findByEmail(email) == null) {
                throw BusinessException.fail(FrontRespEnum.EMAIL_EXIST);
            }
        }
        if(type == 2) {
            if(findByEmail(email) == null) {
                throw BusinessException.fail(FrontRespEnum.EMAIL_NOT_EXIST);
            }
        }

        CaptchaUtil.sendEmail(email,content);

        return true;
    }

    @Override
    public Boolean resetPassword(String email, String password) {
        User user = findByEmail(email);
        user.setPassword(MD5Util.getMD5(password));
        userDao.save(user);
        return true;
    }

    @Override
    public Boolean update(UserUpdateInfoDto infoDto) {
        User user = findById(infoDto.getId());
        user.setNickName(infoDto.getNickName())
                .setSex(infoDto.getSex())
                .setBirthday(Formater.parseDate(infoDto.getBirthday()))
                .setOccupation(infoDto.getOccupation())
                .setArea(infoDto.getArea())
                .setDescription(infoDto.getDescription())
                .setUpdateTime(LocalDateTime.now());
        userDao.save(user);
        return true;
    }

    @Override
    public Boolean updateStatus(UserUpdateStatusDto dto) {
        User user = findById(dto.getUserId());
        user.setStatus(dto.getStatus());
        userDao.save(user);
        return true;
    }

    @Override
    @Transactional
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
            if(StringUtils.isNotEmpty(queryDto.getNickName())){
                list.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+queryDto.getNickName()+"%"));
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
