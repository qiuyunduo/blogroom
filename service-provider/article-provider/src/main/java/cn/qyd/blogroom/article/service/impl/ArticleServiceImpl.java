package cn.qyd.blogroom.article.service.impl;

import cn.qyd.blogroom.article.dao.ArticleDao;
import cn.qyd.blogroom.article.dto.ArticleQueryDto;
import cn.qyd.blogroom.article.entity.Article;
import cn.qyd.blogroom.article.service.ArticleService;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
//            if(queryDto.getId()!=null){
//                list.add(criteriaBuilder.equal(root.get("id").as(Long.class), queryDto.getId()));
//            }
//            if(queryDto.getCreatedDate()!=null){
//                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate").as(LocalDate.class), Formater.parseLocalDate(queryDto.getCreatedDate())));
//            }
            if(StringUtils.isNotEmpty(queryDto.getTitle())){
                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%"+queryDto.getTitle()+"%"));
            }
//            if(StringUtils.isNotEmpty(queryDto.getEmail())){
//                list.add( criteriaBuilder.like(root.get("email").as(String.class), "%"+queryDto.getEmail()+"%"));
//            }
//            if(queryDto.getLocation()!=null){
//                list.add(criteriaBuilder.like(root.get("location").as(String.class), "%"+queryDto.getLocation()+"%"));
//            }
//            if(queryDto.getDemand()!=null){
//                list.add(criteriaBuilder.like(root.get("demand").as(String.class), "%"+queryDto.getDemand()+"%"));
//            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
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
