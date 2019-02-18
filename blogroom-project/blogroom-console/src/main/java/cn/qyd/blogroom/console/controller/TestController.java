package cn.qyd.blogroom.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author qyd
 * @Date 18-12-29 上午11:56
 **/
@RestController
@Configuration
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${User}")
    String name;

    @GetMapping("/getAuthor")
    public String getAuthor(){
        return name;
    }

    @GetMapping("/getUser")
    public String getUser() {
        String json = restTemplate.getForObject("http://user-provider/getUser",String.class);
        return json;
    }
}
