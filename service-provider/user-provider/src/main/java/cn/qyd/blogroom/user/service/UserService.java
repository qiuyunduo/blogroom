package cn.qyd.blogroom.user.service;

import cn.qyd.blogroom.user.dto.*;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.vo.LoginUser;
import org.springframework.data.domain.Page;
import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-18 下午5:52
 **/
public interface UserService {
    User save(UserDto dto);

    LoginUser login(String account, String password);

    LoginUser register(UserDto dto);

    Boolean logout(Long userId);

    User findByAccount(String account);

    User findById(Long id);

    User findByEmail(String email);

    User findByPhone(String phone);

    Page<User> query(UserQueryDto queryDto);

    List<User> fashionUser();

    Long countAll();

    Boolean updateImage(Long userId, String newImage);

    Boolean updatePassword(UpdatePwdDto dto);

    Boolean sendValidateCode(String email, Integer type);

    Boolean validateCode(String email, Integer type, String code);

    Boolean resetPassword(String email, String password);

    Boolean update(UserUpdateInfoDto infoDto);

    Boolean updateStatus(UserUpdateStatusDto dto);

    Boolean delete(Long id);

//    List<User> selectFashionUser();
//
//    User selectByNamePwd(String name,String pwd);
//
//    User selectAdmin(String name,String pwd);
//
//    User selectById(Integer userId);
//
//    int deleteById(Integer userId);
//
//    int updateBySelectiveImg(User user);
//
//    int updateBySelectiveInfo(User user);
//
//    int updateBySelectivePwd(User user);
//
//    int updateBySelectiveMark(User user);
}
