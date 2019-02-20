package cn.qyd.blogroom.console.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface ArticleService {
    JSONArray findAll();

    JSONObject findOne(Integer articleId);
}
