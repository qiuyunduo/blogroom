package cn.qyd.blogroom.user.dao;

import cn.qyd.blogroom.user.entity.Attention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author qyd
 * @Date 19-2-26 下午1:11
 **/
public interface AttentionDao extends JpaRepository<Attention, Long> {
    Page<Attention> findAll(Specification<Attention> specification, Pageable pageable);
}
