package cn.qyd.blogroom.user.dao;

import cn.qyd.blogroom.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author qyd
 * @Date 19-2-18 下午5:50
 **/
public interface UserDao extends JpaRepository<User,Integer> {
}
