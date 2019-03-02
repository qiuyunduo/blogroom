package cn.qyd.blogroom.blog.service.impl;

import cn.qyd.blogroom.blog.dao.IntegralLogDao;
import cn.qyd.blogroom.blog.dto.BlogIntegralChangeDto;
import cn.qyd.blogroom.blog.entity.Blog;
import cn.qyd.blogroom.blog.entity.IntegralLog;
import cn.qyd.blogroom.blog.entity.Rank;
import cn.qyd.blogroom.blog.service.BlogService;
import cn.qyd.blogroom.blog.service.IntegralCongfigService;
import cn.qyd.blogroom.blog.service.IntegralLogService;
import cn.qyd.blogroom.blog.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author qyd
 * @Date 19-2-27 上午11:38
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IntegralLogServiceImpl implements IntegralLogService {
    private final IntegralLogDao integralLogDao;

    private final BlogService blogService;

    private final IntegralCongfigService integralCongfigService;

    private final RankService rankService;

    @Override
    public IntegralLog save(IntegralLog integralLog) {
        Blog blog = blogService.findByUserId(integralLog.getUserId());
        Integer number = integralCongfigService.findById(integralLog.getTypeId()).getNumber();
        Rank rank = rankService.findByIntegral(blog.getIntegral());
        BlogIntegralChangeDto dto = new BlogIntegralChangeDto();
        dto.setId(blog.getId());
        dto.setIntegral(blog.getIntegral()+number);
        dto.setRankId(rank.getId());
        blogService.updateIntegral(dto);
        IntegralLog result = integralLogDao.save(integralLog);
        return result;
    }
}
