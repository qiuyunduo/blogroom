//package cn.qyd.blogroom.gateway.config;
//
//import cn.qyd.blogroom.gateway.filter.SwaggerHeaderFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RouteConfig {
//
//    @Autowired
//    private SwaggerHeaderFilter swaggerHeaderFilter;
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("path_route", r -> r.path("/user/**")
//                        .filters(f -> f.stripPrefix(1))
//                        .uri("lb://user-provider"))
//                .build();
//    }
//}
