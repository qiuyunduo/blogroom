package cn.qyd.blogroom.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author qyd
 * @Date 19-2-20 上午11:40
 **/
@SpringBootApplication(scanBasePackages = "cn.qyd.blogroom")
@EnableEurekaClient
@EnableSwagger2
public class BlogServiceProvider {
    public static void main(String[] args) {
        SpringApplication.run(BlogServiceProvider.class,args);
    }
}
