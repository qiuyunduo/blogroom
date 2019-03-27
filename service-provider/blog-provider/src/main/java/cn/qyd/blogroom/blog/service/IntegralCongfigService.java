package cn.qyd.blogroom.blog.service;

import cn.qyd.blogroom.blog.dto.IntegralConfigDto;
import cn.qyd.blogroom.blog.entity.IntegralConfig;
import java.util.List;
public interface IntegralCongfigService {
    List<IntegralConfig> all();
    IntegralConfig findById(Long id);

    IntegralConfig findByType(Integer type);

    Boolean update(IntegralConfigDto dto);
}
