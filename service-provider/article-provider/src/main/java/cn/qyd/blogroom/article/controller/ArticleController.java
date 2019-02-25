package cn.qyd.blogroom.article.controller;

import cn.qyd.blogroom.article.dto.ArticleDto;
import cn.qyd.blogroom.article.dto.ArticleQueryDto;
import cn.qyd.blogroom.article.dto.ArticleStatusDto;
import cn.qyd.blogroom.article.entity.Article;
import cn.qyd.blogroom.article.service.ArticleService;
import cn.qyd.blogroom.article.vo.ArticleVo;
import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import cn.qyd.blogroom.common.utils.PagingUtil;
import cn.qyd.blogroom.common.utils.dozer.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-21 下午6:09
 **/
@RestController
@RequestMapping("/info")
@Api(tags = "文章模块相关")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/save")
    @ApiOperation("添加文章")
    public Resp addArticle(ArticleDto dto){
        Article result = articleService.save(dto);
        return Resp.succeed(result);
    }

    @GetMapping("/all")
    @ApiOperation("根据条件获取所有相应的文章（首页展示，博客空间等多个地方用到）")
    public Resp all(ArticleQueryDto dto) {
        Page<Article> resultPage = articleService.query(dto);
        PagingInfo pageInfo = PagingUtil.page(resultPage);
        pageInfo.setItem(BeanMapper.mapList(pageInfo.getItem(), ArticleVo.class));
        return Resp.succeedPaging(pageInfo);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据文章id获取文章")
    public Resp all(@PathVariable("id") Long id) {
        Article article = articleService.findById(id);
        ArticleVo articleVo = BeanMapper.map(article, ArticleVo.class);
        return Resp.succeed(articleVo);
    }

    @GetMapping("/fashion")
    @ApiOperation("获取时尚博文")
    public Resp fashionArticles() {
        List<Article> articles = articleService.fashionArticles();
        List<ArticleVo> articleVos = BeanMapper.mapList(articles, ArticleVo.class);
        return Resp.succeed(articleVos);
    }

    @PutMapping("/update")
    @ApiOperation("修改博客文章内容")
    public Resp updateArticle(ArticleDto dto) {
        Boolean result = articleService.update(dto);
        return Resp.succeed(result);
    }

    @PutMapping("/updateStatus")
    @ApiOperation("修改文章信息状态")
    public Resp updateArticleStatus(ArticleStatusDto statusDto) {
        Boolean result = articleService.updateStatus(statusDto);
        return Resp.succeed(result);
    }

    @DeleteMapping("/delete/{articleId}")
    @ApiOperation("修改文章信息状态")
    public Resp updateArticleStatus(@PathVariable("articleId") Long articleId) {
        Boolean result = articleService.delete(articleId);
        return Resp.succeed(result);
    }
}
