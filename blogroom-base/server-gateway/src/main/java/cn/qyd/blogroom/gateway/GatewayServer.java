package cn.qyd.blogroom.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author qyd
 * @Date 18-12-29 下午5:05
 **/
@SpringBootApplication
@EnableEurekaClient
public class GatewayServer {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServer.class,args);
    }
}
