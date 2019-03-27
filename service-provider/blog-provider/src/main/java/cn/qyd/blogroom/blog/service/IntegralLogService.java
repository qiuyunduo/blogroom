package cn.qyd.blogroom.blog.service;

import cn.qyd.blogroom.blog.dto.IntegralLogQueryDto;
import cn.qyd.blogroom.blog.dto.IntegralLogDto;
import cn.qyd.blogroom.blog.entity.IntegralLog;
import org.springframework.data.domain.Page;

public interface IntegralLogService {
    IntegralLog save(IntegralLogDto integralLogDto);

    Page<IntegralLog> query(IntegralLogQueryDto queryDto);
}
