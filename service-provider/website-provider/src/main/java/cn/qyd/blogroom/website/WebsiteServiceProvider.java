package cn.qyd.blogroom.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author qyd
 * @Date 19-1-2 上午10:56
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("cn.qyd.blogroom.client.**")
@EnableSwagger2
public class WebsiteServiceProvider {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteServiceProvider.class,args);
    }
}
