package cn.qyd.blogroom.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author qyd
 * @Date 18-12-29 上午11:11
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@Slf4j
@EnableSwagger2
public class ConsoleApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class,args);
        log.info("===================");
    }
}
