package com.belong.codeChallenge;

import java.util.Collections;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.belong.codeChallenge")).paths(PathSelectors.any())
				.build();
	}

	protected ApiInfo apiInfo() {
		return new ApiInfo(this.title(), this.description(), "", "",
				new Contact("Veeneta Ray", "", "vinni13@gmail.com"), "", "", Collections.emptyList());
	}

	public String description() {
		return "Provides a set of endpoints for telstra <br>" + "There are following 3 Endpoints in this API<br>"
				+ "1) get all phone numbers <br>" + "2) get all phone numbers of a single customer<br>"
				+ "3) activate a phone number";
	}

	public String title() {
		return "Code Challenge - Telstra";
	}
}
