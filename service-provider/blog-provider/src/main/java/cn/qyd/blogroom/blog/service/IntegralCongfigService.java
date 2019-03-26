package cn.qyd.blogroom.blog.service;

import cn.qyd.blogroom.blog.dto.IntegralConfigDto;
import cn.qyd.blogroom.blog.entity.IntegralConfig;

public interface IntegralCongfigService {
    IntegralConfig findById(Long id);

    IntegralConfig findByType(Integer type);

    Boolean update(IntegralConfigDto dto);
}
