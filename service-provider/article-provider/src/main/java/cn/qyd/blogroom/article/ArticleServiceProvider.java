package cn.qyd.blogroom.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author qyd
 * @Date 18-12-29 上午11:48
 **/
@SpringBootApplication
@ComponentScan("cn.qyd.blogroom")
@EnableEurekaClient
@EnableFeignClients("cn.qyd.blogroom.client.**")
@EnableSwagger2
public class ArticleServiceProvider {
    public static void main(String[] args) {
        SpringApplication.run(ArticleServiceProvider.class,args);
    }
}
