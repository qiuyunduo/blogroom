package cn.qyd.blogroom.console.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author qyd
 * @Date 18-12-29 上午11:56
 **/
@RestController
public class TestController {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Value("${author.name}")
    String name;

    @GetMapping("/getAuthor")
    public String getAuthor(){
        return name;
    }

    @GetMapping("/getUser")
    public String getUser() {
        RestTemplate restTemplate = getRestTemplate();
        String json = restTemplate.getForObject("http://user-provider/getUser",String.class);

        return json;
    }
}
