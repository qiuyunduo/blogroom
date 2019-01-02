package cn.qyd.blogroom.common.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db.properties")
@Slf4j
public class DataSourceConfig {

    /**
     * druid配置
     */
    @Bean("druidProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        log.info("=================");
        return new DruidProperties();
    }

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource(@Qualifier("druidProperties") DruidProperties druidProperties) {
        log.info("=============");
        log.info(druidProperties.getUrl());
        log.info(druidProperties.toString());
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }
}
