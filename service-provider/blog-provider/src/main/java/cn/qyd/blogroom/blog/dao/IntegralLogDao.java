package cn.qyd.blogroom.blog.dao;

import cn.qyd.blogroom.blog.entity.IntegralLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author qyd
 * @Date 19-2-27 上午10:38
 **/
public interface IntegralLogDao extends JpaRepository<IntegralLog,Long> {
}
