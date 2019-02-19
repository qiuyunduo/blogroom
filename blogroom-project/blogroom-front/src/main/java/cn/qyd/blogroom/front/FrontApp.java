package cn.qyd.blogroom.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author qyd
 * @Date 19-1-2 上午10:29
 **/
@SpringBootApplication
@EnableSwagger2
public class FrontApp {
    public static void main(String[] args) {
        SpringApplication.run(FrontApp.class,args);
    }
}
