package cn.qyd.blogroom.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";
    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    //    @Override
    //    public List<SwaggerResource> get() {
    //        List<SwaggerResource> resources = new ArrayList<>();
    //
    //        resources.add(swaggerResource("Attach-模块", "/attach/v2/api-docs"));
    //        resources.add(swaggerResource("Info-模块", "/info/v2/api-docs"));
    //        resources.add(swaggerResource("Market-模块", "/market/v2/api-docs"));
    //        resources.add(swaggerResource("Notify-模块", "/notify/v2/api-docs"));
    //        resources.add(swaggerResource("Other-模块", "/other/v2/api-docs"));
    //        resources.add(swaggerResource("Trade-模块", "/trade/v2/api-docs"));
    //        resources.add(swaggerResource("Wallet-模块", "/wallet/v2/api-docs"));
    //
    //        return resources;
    //    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        // 取出gateway的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        System.out.println(routes);
        // 结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        gatewayProperties.getRoutes().stream()
                .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> resources.add(
                                swaggerResource(routeDefinition.getId(),
                                        predicateDefinition
                                                .getArgs()
                                                .get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                                .replace("/**", API_URI)))));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
