package cn.qyd.blogroom.article.controller;

import cn.qyd.blogroom.article.entity.ArticleClass;
import cn.qyd.blogroom.article.service.ArticleClassService;
import cn.qyd.blogroom.article.vo.ArticleClassVo;
import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.common.utils.dozer.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-3-13 下午12:10
 **/
@RestController
@RequestMapping("class")
@Api(tags = "文章类别模块")
public class ArticleClassController {

    @Autowired
    private ArticleClassService articleClassService;

    @GetMapping("/all")
    @ApiOperation("获得所有文章类别")
    public Resp allArticleClass() {
        List<ArticleClass> articleClasses = articleClassService.all();
        List<ArticleClassVo> articleClassVos = BeanMapper.mapList(articleClasses, ArticleClassVo.class);
        return Resp.succeed(articleClassVos);
    }
}
