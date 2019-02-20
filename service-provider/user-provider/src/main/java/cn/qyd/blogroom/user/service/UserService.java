package cn.qyd.blogroom.user.service;

import cn.qyd.blogroom.user.entity.User;
import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-18 下午5:52
 **/
public interface UserService {
    int insertOne(User user);

    List<User> selectAll();

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
