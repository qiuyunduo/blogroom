package cn.qyd.blogroom.blog.service;

import cn.qyd.blogroom.blog.dto.BlogIntegralChangeDto;
import cn.qyd.blogroom.blog.dto.BlogStatusDto;
import cn.qyd.blogroom.blog.entity.Blog;

public interface BlogService {
    Blog save(Long userid);

    Blog findByUserId(Long userid);

    Boolean updateIntegral(BlogIntegralChangeDto dto);

    Boolean updateBlogVisit(Long id);

    Boolean updateBlogStatus(BlogStatusDto dto);
}
