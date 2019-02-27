package cn.qyd.blogroom.website.dao;

import cn.qyd.blogroom.website.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author qyd
 * @Date 19-2-27 下午1:27
 **/
public interface AdminDao extends JpaRepository<Admin,Long> {
    Admin findByName(String name);
}
