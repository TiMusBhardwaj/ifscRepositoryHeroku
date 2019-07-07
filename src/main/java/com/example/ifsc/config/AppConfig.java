package com.example.ifsc.config;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.ifsc.dto.BranchDetails;
import com.example.ifsc.entities.Branch;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EntityScan(basePackages="com.example.ifsc.entities")
@EnableJpaRepositories(basePackages="com.example.ifsc.repository")
@EnableSwagger2
@Slf4j
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    modelMapper.createTypeMap(Branch.class, BranchDetails.class);
	    return modelMapper;
	}
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.ifsc.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Ifsc Information API", "Ifsc Information API", "v1", "Terms of service",
				new Contact("Sumit Bhardwaj", "www.example.com", "sumit.bhardwaj@company.com"), "License of API",
				"API license URL", Collections.emptyList());
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			if (!log.isDebugEnabled()) {
				return;
			}
			log.debug("Beans Loaded by Spring-------------------");

			String[] beanNames = ctx.getBeanDefinitionNames();
			for (String beanName : beanNames) {
				log.debug(beanName);
			}
			log.debug("-----------------------------------------");
		};
	}

}