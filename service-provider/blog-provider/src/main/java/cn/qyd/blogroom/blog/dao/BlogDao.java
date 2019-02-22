package cn.qyd.blogroom.blog.dao;

import cn.qyd.blogroom.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogDao extends JpaRepository<Blog,Long> {
}
