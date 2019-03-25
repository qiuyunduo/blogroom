package cn.qyd.blogroom.blog.service;

import cn.qyd.blogroom.blog.dto.RankDto;
import cn.qyd.blogroom.blog.entity.Rank;
import java.util.List;
public interface RankService {
    Rank findById(Long id);

    Rank findByIntegral(Integer integer);

    List<Rank> all();

    Boolean update(RankDto dto);
}
