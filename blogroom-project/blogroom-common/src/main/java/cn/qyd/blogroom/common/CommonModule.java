package cn.qyd.blogroom.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author qyd
 * @Date 18-12-29 下午2:10
 **/
@SpringBootApplication
@MapperScan("cn.qyd.blogroom.*.mapper")
public class CommonModule {
}
