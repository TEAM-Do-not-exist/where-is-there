package com.ssafy.project1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		.groupName("project1")
		.apiInfo(info())
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.ssafy.project1.controller"))
		.paths(PathSelectors.ant("/api/**"))
		.build();
	}
	
	public ApiInfo info() {
		return new ApiInfoBuilder()
				.title("project1 API")
				.license("project1 SSAFY")
				.version("1.0")
				.build();
	}	
}
