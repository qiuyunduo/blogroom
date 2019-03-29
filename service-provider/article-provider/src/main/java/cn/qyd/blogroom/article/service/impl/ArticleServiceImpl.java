package cn.qyd.blogroom.article.service.impl;

import cn.qyd.blogroom.article.dao.ArticleDao;
import cn.qyd.blogroom.article.dto.ArticleDto;
import cn.qyd.blogroom.article.dto.ArticleQueryDto;
import cn.qyd.blogroom.article.dto.ArticleStatusDto;
import cn.qyd.blogroom.article.entity.Article;
import cn.qyd.blogroom.article.service.ArticleService;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-22 下午12:27
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article save(ArticleDto dto) {
        LocalDateTime now = LocalDateTime.now();
        Article article = new Article();
        article.setClassId(dto.getClassId())
                .setUserId(dto.getUserId())
                .setAuthor(dto.getAuthor())
                .setHeadPhoto(dto.getHeadPhoto())
                .setTitle(dto.getTitle())
                .setIntroduction(dto.getIntroduction())
                .setKeyword(dto.getKeyword())
                .setContent(dto.getContent())
                .setComments(0)
                .setThumbs(0)
                .setStatus(dto.getStatus())
                .setSubmitTime(dto.getStatus() == 1 ? LocalDateTime.now() : null)
                .setUpdateTime(now);
        Article result = articleDao.save(article);
        return result;
    }

    @Override
    public Page<Article> query(ArticleQueryDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage()-1,dto.getLimit());
        ArticleQueryParam param = new ArticleQueryParam(dto);
        Page<Article> articlePage = articleDao.findAll(param,pageable);
        return articlePage;
    }

    @Override
    public Article findById(Long id) {
        return articleDao.findById(id)
                .orElseThrow(() -> BusinessException.fail(FrontRespEnum.ARTICLE_NOT_EXIST));
    }

    @Override
    public List<Article> fashionArticles() {
        Pageable pageable = PageRequest.of(0,10);
        ArticleQueryDto queryDto = new ArticleQueryDto();
        queryDto.setStatus(2);
        ArticleQueryParam param = new ArticleQueryParam(queryDto);
        Page<Article> page = articleDao.findAll(param,pageable);
        return page.getContent();
    }

    @Override
    @Transactional
    public Long countArticlesOfUser(Long id) {
        return articleDao.countByUserId(id);
    }

    @Override
    public Long countAll() {
        return articleDao.countByStatus(2);
    }

    @Override
    public Boolean update(ArticleDto dto) {
        Article article = findById(dto.getId());
        article.setId(dto.getId());
        article.setClassId(dto.getClassId())
                .setTitle(dto.getTitle())
                .setHeadPhoto(dto.getHeadPhoto())
                .setIntroduction(dto.getIntroduction())
                .setKeyword(dto.getKeyword())
                .setContent(dto.getContent())
                .setStatus(dto.getStatus())
                .setSubmitTime(dto.getStatus() == 1 ? LocalDateTime.now() : null)
                .setUpdateTime(LocalDateTime.now());

        articleDao.save(article);
        return true;
    }

    @Override
    public Boolean updateStatus(ArticleStatusDto statusDto) {
        Article article = findById(statusDto.getId());
        if(statusDto.getAddComments() != null && statusDto.getAddComments()){
            article.setComments(article.getComments() + 1);
        }
        if(statusDto.getRemoveComments() != null && statusDto.getRemoveComments()){
            article.setComments(article.getComments() - 1);
        }
        if(statusDto.getAddThumbs() != null && statusDto.getAddThumbs()) {
            article.setThumbs(article.getThumbs() + 1);
        }
        if(statusDto.getRemoveThumbs() != null && statusDto.getRemoveThumbs()) {
            article.setThumbs(article.getThumbs() - 1);
        }

        if(statusDto.getStatus() != null){
            article.setStatus(statusDto.getStatus());
            if(statusDto.getStatus() == 2) {
                article.setPublishTime(LocalDateTime.now());

            }
        }

        articleDao.save(article);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateAuthor(String author, Long userId) {
        articleDao.updateAuthorByUserId(author,userId);
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        articleDao.deleteById(id);
        return true;
    }

    /**
     * 查询条件
     */
    class ArticleQueryParam implements Specification {
        ArticleQueryDto queryDto;
        public ArticleQueryParam(ArticleQueryDto dto ){
            this.queryDto=dto;
        }

        @Override
        public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<Predicate>();
            if(StringUtils.isNotEmpty(queryDto.getKeyWord())){
                list.add(criteriaBuilder.or(criteriaBuilder.like(root.get("title").as(String.class), "%"+queryDto.getKeyWord()+"%"),criteriaBuilder.like(root.get("author").as(String.class), "%"+queryDto.getKeyWord()+"%")));
            }
            if(queryDto.getClassId()!=null){
                list.add(criteriaBuilder.equal(root.get("classId").as(Long.class), queryDto.getClassId()));
            }
            if(queryDto.getUserId()!=null){
                list.add(criteriaBuilder.equal(root.get("userId").as(Long.class), queryDto.getUserId()));
            }
            if(queryDto.getStatus() != null){
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), queryDto.getStatus()));
            }

            Predicate[] p = new Predicate[list.size()];
            criteriaQuery.where(list.toArray(p));
            if(queryDto.getOrderByUpdateTime() == null || queryDto.getOrderByUpdateTime()) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("addTime").as(LocalDateTime.class)));
            }else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("updateTime").as(LocalDateTime.class)));
            }
            return criteriaQuery.getRestriction();
        }
    }


//    @Override
//    public List<Article> findAll() {
//        Article article = new Article();
//        article.setId(17l);
//        article.setTitle("python");
//        System.out.println(article);
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("title",match -> match.contains())
//                .withMatcher("addTime",match -> match.)
//                .withIgnorePaths("addTime");
//        Example<Article> example = Example.of(article,matcher);
//        List<Article> all = articleDao.findAll(example);
//        if(all.size() ==0 || all == null) {
//            return ListUtils.EMPTY_LIST;
//        }
//
//        return all;
//    }
}
