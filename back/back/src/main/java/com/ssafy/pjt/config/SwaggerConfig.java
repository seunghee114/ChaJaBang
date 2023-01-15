package com.ssafy.pjt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:7777/swagger-ui.html
//http://localhost:8888/apt/swagger-ui/index.html

@Configuration
@EnableSwagger2 
public class SwaggerConfig {
	private ApiInfo info() {
		return new ApiInfoBuilder().title("Final Project를 수행할게요~!").build();
	}
	
	@Bean // xml에서 사용되던 <bean> 태그를 대체하는 애노테이션
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(info())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.pjt.controller"))
				.paths(PathSelectors.ant("/**"))
				.build();
	}
}
