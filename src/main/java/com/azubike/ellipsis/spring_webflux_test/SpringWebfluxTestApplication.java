package com.azubike.ellipsis.spring_webflux_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class SpringWebfluxTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxTestApplication.class, args);
	}

}
