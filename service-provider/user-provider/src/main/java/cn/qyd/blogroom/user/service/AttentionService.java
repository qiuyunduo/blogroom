package cn.qyd.blogroom.user.service;

import cn.qyd.blogroom.user.dto.AttentionDto;
import cn.qyd.blogroom.user.dto.AttentionQueryDto;
import cn.qyd.blogroom.user.entity.Attention;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-22 下午4:24
 **/
public interface AttentionService {
    Attention save(AttentionDto dto);

    Attention selectOne(AttentionDto dto);

    Page<Attention> query(AttentionQueryDto dto);

    void delete(AttentionDto dto);
}
