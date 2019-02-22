package cn.qyd.blogroom.gateway;

//import cn.qyd.blogroom.gateway.filter.SwaggerHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

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
