//package cn.qyd.blogroom.gateway.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author qyd
// * @Date 19-2-28 上午10:20
// **/
//@Component
//public class LoginTokenFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        String url = request.getPath().pathWithinApplication().value();
//        if(checkFilterList(url)) {
//            if(request.getHeaders() != null) {
//                if(request.getHeaders().get("token") == null) {
//                    System.out.println("用户未登录");
//                }
//            }else {
//                System.out.println("用户未登录");
//            }
//        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return -200;
//    }
//
//    public Boolean checkFilterList(String path) {
//        if(path().contains(path)) {
//            return true;
//        }
//        return false;
//    }
//
//    public List<String> path() {
//        List<String> urls = new ArrayList<>();
//        urls.add("/user/attention/save");
//        urls.add("/article/comment/save");
//        return urls;
//    }
//}
