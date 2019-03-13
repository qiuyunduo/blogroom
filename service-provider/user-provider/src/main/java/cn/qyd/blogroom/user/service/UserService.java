package cn.qyd.blogroom.user.service;

import cn.qyd.blogroom.user.dto.UpdatePwdDto;
import cn.qyd.blogroom.user.dto.UserDto;
import cn.qyd.blogroom.user.dto.UserQueryDto;
import cn.qyd.blogroom.user.dto.UserUpdateInfoDto;
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

    LoginUser login(String name, String password);

    Boolean logout(String token);

    User findById(Long id);

    User findByEmail(String email);

    User findByPhone(String phone);

    Page<User> query(UserQueryDto queryDto);

    List<User> fashionUser();

    Boolean updatePassword(UpdatePwdDto dto);

    Boolean resetPassword(Long id, String password);

    Boolean update(UserUpdateInfoDto infoDto);

    Boolean updateStatus(Long id, Integer status);

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
