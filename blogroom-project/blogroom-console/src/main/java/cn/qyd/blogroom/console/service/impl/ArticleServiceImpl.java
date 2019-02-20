package cn.qyd.blogroom.console.service.impl;

import cn.qyd.blogroom.console.service.ArticleService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author qyd
 * @Date 19-2-20 下午3:40
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONArray findAll() {
        JSONArray articles = restTemplate.getForObject("http://article-provider/article/all", JSONArray.class);
        return articles;
    }

    @Override
    public JSONObject findOne(Integer articleId) {
        Map<String,Integer> map = new HashMap<>();
        map.put("articleId",articleId);
        JSONObject article = restTemplate.getForObject("http://article-provider/article", JSONObject.class,map);
        return article;
    }
}
