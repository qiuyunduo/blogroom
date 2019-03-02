package cn.qyd.blogroom.blog.service;

import cn.qyd.blogroom.blog.entity.Rank;

public interface RankService {
    Rank findById(Long id);

    Rank findByIntegral(Integer integer);
}
