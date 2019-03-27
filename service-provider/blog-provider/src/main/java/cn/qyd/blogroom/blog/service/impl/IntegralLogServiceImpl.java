package cn.qyd.blogroom.blog.service.impl;

import cn.qyd.blogroom.blog.dao.IntegralLogDao;
import cn.qyd.blogroom.blog.dto.IntegralLogQueryDto;
import cn.qyd.blogroom.blog.dto.BlogIntegralChangeDto;
import cn.qyd.blogroom.blog.dto.IntegralLogDto;
import cn.qyd.blogroom.blog.entity.Blog;
import cn.qyd.blogroom.blog.entity.IntegralLog;
import cn.qyd.blogroom.blog.entity.Rank;
import cn.qyd.blogroom.blog.service.BlogService;
import cn.qyd.blogroom.blog.service.IntegralCongfigService;
import cn.qyd.blogroom.blog.service.IntegralLogService;
import cn.qyd.blogroom.blog.service.RankService;
import cn.qyd.blogroom.common.utils.Formater;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public IntegralLog save(IntegralLogDto integralLogDto) {
        Blog blog = blogService.findByUserId(integralLogDto.getUserId());
        Integer number = integralCongfigService.findById(integralLogDto.getType()).getNumber();
        Integer integral = blog.getIntegral()+ number;
        Rank rank = rankService.findByIntegral(integral);
        BlogIntegralChangeDto dto = new BlogIntegralChangeDto();
        dto.setId(blog.getId());
        dto.setIntegral(integral);
        dto.setRankId(rank.getId());
        blogService.updateIntegral(dto);

        IntegralLog integralLog = new IntegralLog();
        integralLog.setArticleId(integralLogDto.getArticleId())
                .setBlogId(blog.getId())
                .setType(integralLogDto.getType())
                .setAddTime(LocalDateTime.now());
        IntegralLog result = integralLogDao.save(integralLog);
        return result;
    }

    @Override
    public Page<IntegralLog> query(IntegralLogQueryDto queryDto) {
        Pageable pageable = PageRequest.of(queryDto.getPage()-1,queryDto.getLimit());
        IntegralLogQueryParam param = new IntegralLogQueryParam(queryDto);
        Page<IntegralLog> integralLogPage = integralLogDao.findAll(param,pageable);
        return integralLogPage;
    }

    /**
     * 查询条件
     */
    class IntegralLogQueryParam implements Specification {
        IntegralLogQueryDto queryDto;
        public IntegralLogQueryParam(IntegralLogQueryDto dto ){
            this.queryDto=dto;
        }

        @Override
        public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<Predicate>();
            if(queryDto.getBlogId() != null){
                list.add(criteriaBuilder.like(root.get("blogId").as(String.class), "%"+queryDto.getBlogId()+"%"));
            }
            if(queryDto.getType() != null){
                list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), queryDto.getType()));
            }
            if(queryDto.getArticleId() != null){
                list.add(criteriaBuilder.equal(root.get("articleId").as(Integer.class), queryDto.getArticleId()));
            }
            if(queryDto.getStartTime() != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("addTime").as(LocalDateTime.class), Formater.parseLocalDateTime(queryDto.getStartTime())));
            }
            if(queryDto.getEndTime() != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("addTime").as(LocalDateTime.class),Formater.parseLocalDateTime(queryDto.getEndTime())));
            }

            Predicate[] p = new Predicate[list.size()];
            criteriaQuery.where(list.toArray(p));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("addTime").as(LocalDateTime.class)));
            return criteriaQuery.getRestriction();
        }
    }
}
