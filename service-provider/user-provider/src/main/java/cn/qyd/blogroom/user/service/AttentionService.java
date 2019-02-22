package cn.qyd.blogroom.user.service;

import cn.qyd.blogroom.user.entity.Attention;
import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-22 下午4:24
 **/
public interface AttentionService {
    int deleteBySelective(Attention attention);

    int insertOne(Attention attention);

    //查询某一用户所有关注的用户（关注）
    List<Integer> selectAllOfGid(Integer Gid);
    //查询所有关注了某一用户的用户（粉丝）
    List<Integer> selectAllOfFid(Integer Fid);
}
