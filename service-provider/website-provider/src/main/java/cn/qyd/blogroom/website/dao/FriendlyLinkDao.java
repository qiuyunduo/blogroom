package cn.qyd.blogroom.website.dao;

import cn.qyd.blogroom.website.entity.FriendlyLink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author qyd
 * @Date 19-2-27 下午1:26
 **/
public interface FriendlyLinkDao extends JpaRepository<FriendlyLink,Long> {
}
