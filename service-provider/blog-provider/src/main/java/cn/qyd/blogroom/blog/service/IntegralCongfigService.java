package cn.qyd.blogroom.blog.service;

import cn.qyd.blogroom.blog.entity.IntegralConfig;

public interface IntegralCongfigService {
    IntegralConfig findById(Long id);

    Boolean update(IntegralConfig integralConfig);
}
