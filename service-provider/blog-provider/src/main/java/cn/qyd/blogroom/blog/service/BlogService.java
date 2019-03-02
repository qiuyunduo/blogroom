package cn.qyd.blogroom.blog.service;

import cn.qyd.blogroom.blog.dto.BlogBaseInfoDto;
import cn.qyd.blogroom.blog.dto.BlogDto;
import cn.qyd.blogroom.blog.dto.BlogIntegralChangeDto;
import cn.qyd.blogroom.blog.dto.BlogStatusDto;
import cn.qyd.blogroom.blog.entity.Blog;

public interface BlogService {
    Blog save(BlogDto dto);

    Blog findByUserId(Long userid);

    Boolean updateIntegral(BlogIntegralChangeDto dto);

    Boolean updateBlog(BlogBaseInfoDto dto);

    Boolean updateBlogStatus(BlogStatusDto dto);
}
