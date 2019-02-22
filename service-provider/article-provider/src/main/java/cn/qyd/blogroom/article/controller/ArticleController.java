package cn.qyd.blogroom.article.controller;

import cn.qyd.blogroom.article.dto.ArticleQueryDto;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/all")
    @ApiOperation("获取所有文章，首页展示用到")
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
        return Resp.succeed(article);
    }
}
