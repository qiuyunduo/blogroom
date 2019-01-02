package cn.qyd.blogroom.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author qyd
 * @Date 19-1-2 上午11:01
 **/
@SpringBootApplication
public class HystrixServer {
    public static void main(String[] args) {
        SpringApplication.run(HystrixServer.class,args);
    }
}
