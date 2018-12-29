package cn.qyd.blogroom.common.config;

import cn.qyd.blogroom.common.constants.Constant;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
				.groupName("BlogRoom")
				.globalOperationParameters(buildParameters())
                .apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
	}

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("模块接口文档")
                .description("API接口列表")
                .contact(new Contact("mybitt", "", ""))
				.version("2.0")
                .build();
	}

	private List<Parameter> buildParameters(){
		List<Parameter> parameterList = new ArrayList<>();

		ParameterBuilder localePar = new ParameterBuilder();
		localePar.name(Constant.APP_LOCALE).description("当前语言:1.zh_CN:中文；2.en_US:英文").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		ParameterBuilder appSystemPar = new ParameterBuilder();
		appSystemPar.name(Constant.APP_SYSTEM).description("APP系统(Android/iOS)").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		ParameterBuilder appVersionPar = new ParameterBuilder();
		appVersionPar.name(Constant.APP_VERSION).description("APP版本号").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		ParameterBuilder tokenPar = new ParameterBuilder();
		tokenPar.name(Constant.HEADER_KEY_AUTHORIZATION).description("认证请求头, Bearer +(钱包token)").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue(Constant.TOKEN_PREFIX).build();
		ParameterBuilder userTokenPar = new ParameterBuilder();
		userTokenPar.name(Constant.USER_TOKEN_KEY).description("认证请求头, Bearer +(用户token[测试环境-刷新时间:1分钟, 过期时间:2分钟]), 说明:所有需要带这个token的请求,监听Responses Header->key=" + Constant.REFRESH_TOKEN_KEY + "返回刷新后的Token").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue(Constant.TOKEN_PREFIX).build();

		parameterList.add(localePar.build());
		parameterList.add(appSystemPar.build());
		parameterList.add(appVersionPar.build());
		parameterList.add(tokenPar.build());
		parameterList.add(userTokenPar.build());
		return parameterList;
	}
	
}
