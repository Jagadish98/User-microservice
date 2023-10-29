package com.mobileskins.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
		
		info=@Info(
				title = "User Service REST APIs",
				description = "User Service REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Jagadish M",
						email = "jagadishgowda.m1@gmail.com"
						)
				)
		
		)
@SpringBootApplication
@EntityScan("com.mobileskins.userservice.entity")
@EnableJpaRepositories("com.mobileskins.userservice.reprository")
@ComponentScan({"com.mobileskins.userservice.controller", "com.mobileskins.userservice.exception", 
				"com.mobileskins.userservice.service","com.mobileskins.userservice.dto", "com.mobileskins.userservice.security"})	
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
