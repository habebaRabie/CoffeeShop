package com.example.onlinecoffeeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class OnlineCoffeeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCoffeeShopApplication.class, args);
	}

}

//http://localhost:8080/swagger-ui/index.html#/