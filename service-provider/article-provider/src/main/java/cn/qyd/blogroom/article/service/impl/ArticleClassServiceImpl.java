package cn.qyd.blogroom.article.service.impl;

import cn.qyd.blogroom.article.dao.ArticleClassDao;
import cn.qyd.blogroom.article.entity.ArticleClass;
import cn.qyd.blogroom.article.service.ArticleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-3-13 下午12:08
 **/
@Service
public class ArticleClassServiceImpl implements ArticleClassService {
    @Autowired
    private ArticleClassDao articleClassDao;

    @Override
    public List<ArticleClass> all() {
        List<ArticleClass> articleClasses = articleClassDao.findAll();
        return articleClasses;
    }
}
