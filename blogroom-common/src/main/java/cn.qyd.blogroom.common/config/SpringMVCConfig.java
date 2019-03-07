package cn.qyd.blogroom.common.config;

import cn.qyd.blogroom.common.interceptors.TokenCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMVCConfig extends WebMvcConfigurationSupport {

    @Autowired(required = false)
    private TokenCheckInterceptor tokenCheckInterceptor;

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
//        if (abstractInterceptors != null && abstractInterceptors.size() > 0) {
//            abstractInterceptors.forEach(abstractInterceptor -> {
//        String[] path = tokenCheckInterceptor.path();
//        String[] excludePath = tokenCheckInterceptor.excludePath();
//        InterceptorRegistration registration = registry.addInterceptor(tokenCheckInterceptor);
//        registration.addPathPatterns(path);
//        if (excludePath != null) {
//            registration.excludePathPatterns(excludePath);
//        }
    }

}
