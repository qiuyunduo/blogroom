package cn.qyd.blogroom.blog.dao;

import cn.qyd.blogroom.blog.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @Author qyd
 * @Date 19-2-27 上午9:49
 **/
public interface RankDao extends JpaRepository<Rank,Long> {
    List<Rank> findAllByIntegralLessThanEqual(Integer integer);
}
