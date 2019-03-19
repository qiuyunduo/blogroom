package cn.qyd.blogroom.user.dao;

import cn.qyd.blogroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author qyd
 * @Date 19-2-18 下午5:50
 **/
public interface UserDao extends JpaRepository<User,Long> {
    Page<User> findAll(Specification<User> specification, Pageable pageable);

    User findByAccountAndPassword(String account, String password);

    User findByAccount(String account);

    User findByPhone(String phone);

    User findByEmail(String email);
}
