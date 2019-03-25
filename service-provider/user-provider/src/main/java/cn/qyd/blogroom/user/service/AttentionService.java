package cn.qyd.blogroom.user.service;

import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import cn.qyd.blogroom.user.dto.AttentionDto;
import cn.qyd.blogroom.user.dto.AttentionQueryDto;
import cn.qyd.blogroom.user.entity.Attention;
import cn.qyd.blogroom.user.vo.SimpleUserVo;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-22 下午4:24
 **/
public interface AttentionService {
    Attention save(AttentionDto dto);

    Attention selectOne(AttentionDto dto);

    PagingInfo pageFans(AttentionQueryDto queryDto);

    PagingInfo pageFollowers(AttentionQueryDto queryDto);

    List<SimpleUserVo> followersAll(Long user1Id);

    List<SimpleUserVo> fansAll(Long user2Id);

    void delete(AttentionDto dto);

//    Page<Attention> query(AttentionQueryDto dto);
}
