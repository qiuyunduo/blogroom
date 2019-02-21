package cn.qyd.blogroom.gateway.config;

import cn.qyd.blogroom.gateway.filter.UrlList;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class RouteConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        Map<String, String[]> map = new HashMap<>();
        map.put("user-provider", UrlList.USER_URL);
        map.put("blog-provider", UrlList.BLOG_URL);
        map.put("article-provider", UrlList.ARTICLE_URL);
        map.put("website-provider", UrlList.WEBSITE_URL);

        RouteLocatorBuilder.Builder routes = builder.routes();
        map.forEach((k, v) -> {
            Arrays.stream(v).forEach(url -> {
                if (StringUtils.isBlank(url)) {
                    return;
                }
                String id = UUID.randomUUID().toString();
                routes.route(id, r -> r.path(url)
                        .uri("lb://" + k));
            });
        });
        for(Map.Entry<String,String[]> entry : map.entrySet()) {
            System.err.println(entry.getKey());
            if(entry.getValue().length > 0) {
                System.out.println(entry.getValue()[0]);
            }
        }
        System.err.println(routes.toString());

        return routes.build();
    }
}
