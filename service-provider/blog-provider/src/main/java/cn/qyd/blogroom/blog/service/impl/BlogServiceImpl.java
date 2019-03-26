package cn.qyd.blogroom.blog.service.impl;

import cn.qyd.blogroom.blog.dao.BlogDao;
import cn.qyd.blogroom.blog.dto.BlogIntegralChangeDto;
import cn.qyd.blogroom.blog.dto.BlogStatusDto;
import cn.qyd.blogroom.blog.entity.Blog;
import cn.qyd.blogroom.blog.service.BlogService;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 19-2-27 上午11:01
 **/
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog save(Long userId) {
        Blog blog = new Blog();
        blog.setUserId(userId)
                .setRankId(1l)
                .setStatus(0)
                .setVisitNumber(0)
                .setAddTime(LocalDateTime.now());
        Blog result = blogDao.save(blog);
        return result;
    }

    @Override
    public Blog findByUserId(Long userId) {
        return blogDao.findByUserId(userId)
                .orElseThrow(() -> BusinessException.fail(FrontRespEnum.BLOG_NOT_ACTIVE));
    }

    @Override
    public Boolean updateIntegral(BlogIntegralChangeDto dto) {
        Blog blog = findById(dto.getId());
        if(dto.getIntegral() != null) {
            blog.setIntegral(dto.getIntegral());
        }
        if(dto.getRankId() != null) {
            blog.setRankId(dto.getRankId());
        }
        blogDao.save(blog);
        return true;
    }

    @Override
    public Boolean updateBlogVisit(Long id) {
        Blog blog = findById(id);
        blog.setVisitNumber(blog.getVisitNumber()+1);
        blogDao.save(blog);
        return true;
    }


    @Override
    public Boolean updateBlogStatus(BlogStatusDto dto) {
        Blog blog = findById(dto.getId());
        if(dto.getStatus() != null) {
            blog.setStatus(dto.getStatus());
        }

        blogDao.save(blog);
        return true;
    }

    public Blog findById(Long id) {
        return blogDao.findById(id)
                .orElseThrow(() -> BusinessException.fail(FrontRespEnum.BLOG_NOT_EXIST));
    }
}
