package cn.qyd.blogroom.blog.service.impl;

import cn.qyd.blogroom.blog.dao.RankDao;
import cn.qyd.blogroom.blog.dto.RankDto;
import cn.qyd.blogroom.blog.entity.Rank;
import cn.qyd.blogroom.blog.service.RankService;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public List<Rank> all() {
        return rankDao.findAll();
    }

    @Override
    public Boolean update(RankDto dto) {
        Rank rank = findById(dto.getId());
        if(StringUtils.isNotBlank(dto.getName())) {
            rank.setName(dto.getName());
        }

        if(dto.getIntegral() != null) {
            rank.setIntegral(dto.getIntegral());
        }

        rank.setUpdateTime(LocalDateTime.now());

        rankDao.save(rank);
        return true;
    }
}
