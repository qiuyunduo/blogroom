package cn.qyd.blogroom.blog.service.impl;

import cn.qyd.blogroom.blog.dao.RankDao;
import cn.qyd.blogroom.blog.entity.Rank;
import cn.qyd.blogroom.blog.service.RankService;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-27 上午11:30
 **/
@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankDao rankDao;

    @Override
    public Rank findById(Long id) {
        return rankDao.findById(id)
                .orElseThrow(() -> BusinessException.fail(FrontRespEnum.RANK_ERROR));
    }

    @Override
    public Rank findByIntegral(Integer integer) {
        List<Rank> ranks = rankDao.findAllByIntegralLessThanEqual(integer);
        return ranks.get(ranks.size()-1);
    }
}
