package cn.qyd.blogroom.article.service;

import cn.qyd.blogroom.article.dto.ArticleQueryDto;
import cn.qyd.blogroom.article.entity.Article;
import org.springframework.data.domain.Page;

import java.util.List;
/**
 * @Author qyd
 * @Date 19-2-22 下午12:26
 **/
public interface ArticleService {
    Page<Article> query(ArticleQueryDto dto);

    Article findById(Long id);

//    int insertOne(Article article);
//    List<Article> selectAll();
//    List<Article> searchSome(String title);
//    List<Article> selectFashionArticle();
//    //获得所有未审核通过的文章
//    List<Article> selectAllUnchecked();
//    //获得所有已审核通过的文章
//    List<Article> selectAllChecked();
//    //获得一个用户的所有已经审核通过的文章
//    List<Article> selectAllCheckedByUserid(Integer userid);
//    //获得一个用户的所有正在审核通过的文章
//    List<Article> selectAllUncheckByUserid(Integer userid);
//    //获得属于一个用户的所有文章
//    List<Article> selectAllOfUserid(Integer userid);
//    List<Article> selectAllByClassid(Integer classid);
//    Integer selectCommentsOfUser(Integer userid);
//    Integer selectlikesOfUser(Integer userid);
//    Article selectOneById(Long aid);
//    int deleteOneByAid(Long aid);
//    int updateOneBySelective(Article article);
//    int updateCommentsBySelective(Article article);
}
