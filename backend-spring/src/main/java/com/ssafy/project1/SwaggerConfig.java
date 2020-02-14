package com.ssafy.project1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.project1.authorization.JwtInterceptor;

import springfox.documentation.service.Parameter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	private static final String[] EXCLUDE_PATHS = { "/swagger-ui.html", "/swagger-resources/**", "/error",
			"/webjars/**", "/api/member/**", "/api/jwt/jwt", "/api/emailauth/**",
			// "/api/photo/**",
			"/api/external/**", "/api/photo/selectOne/**", "/api/photo/selectList", "/api/photo/selectListFavorite",
			"/api/favorite/selectPhotoList/**", "/api/comment/selectPhotoList/**"
			// "/api/**"
	};

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		// registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		// registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATHS);
	}

	@Bean
	public Docket api() {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("Authorization") // 헤더 이름
				.description("Access Tocken") // 설명
				.modelRef(new ModelRef("string")).parameterType("header").required(false).build();

		List<Parameter> aParameters = new ArrayList<>();
		aParameters.add(aParameterBuilder.build());
		return new Docket(DocumentationType.SWAGGER_2).groupName("project1").globalOperationParameters(aParameters)
				.apiInfo(info()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();

	}

	// @Bean
	// public Docket postApi() {
	// return new Docket(DocumentationType.SWAGGER_2)
	// .groupName("project1")
	// .apiInfo(info())
	// .select()
	// .apis(RequestHandlerSelectors.basePackage("com.ssafy.project1.controller"))
	// .paths(PathSelectors.ant("/api/**"))
	// .build();
	// }

	public ApiInfo info() {
		return new ApiInfoBuilder().title("project1 API").license("project1 SSAFY").version("1.0").build();
	}

}
