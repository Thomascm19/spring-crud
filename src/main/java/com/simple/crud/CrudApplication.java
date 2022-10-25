package com.simple.crud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication()
@EnableSwagger2
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.simple.crud.application.controller"))
				.build()
				.apiInfo(getApiInfo());

	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Person Info API",
				"Person Info API",
				"1.0",
				"https://gitlab.com/thomas.caycedo/spring-boot-crud",
				new Contact("Thomas", "https://gitlab.com/thomas.caycedo/spring-boot-crud", "thomas.caycedo@pragma.com.co"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
		);
	}
}
