package cn.qyd.blogroom.console.controller;

import cn.qyd.blogroom.console.service.ArticleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qyd
 * @Date 19-2-18 下午5:35
 **/
@RestController
@RequestMapping("/article")
@Api(tags = "文章模块")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/all")
    @ApiOperation("获取所有文章")
    public JSONArray all() {
        JSONArray articles = articleService.findAll();
        return articles;
    }

    @GetMapping
    @ApiOperation("获取某一id的文章")
    public JSONObject one(Integer articleId) {
        JSONObject article = articleService.findOne(articleId);
        return article;
    }

    @PostMapping("/test")
    public Object test(JSONObject object) {
        JSONObject json = (JSONObject) JSON.toJSON(object);
        System.out.println(object);
        System.out.println(object.toString());
        System.out.println(ObjectUtils.identityToString(object));
        System.out.println(org.springframework.util.ObjectUtils.identityToString(object));

        System.out.println(json);
        System.out.println(json.toJSONString());
        return object;
    }
}
