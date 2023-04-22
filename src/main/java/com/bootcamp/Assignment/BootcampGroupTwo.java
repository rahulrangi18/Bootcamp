package com.bootcamp.Assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication()
@EnableJpaRepositories(basePackages = "com.bootcamp.Assignment.repositories")
public class BootcampGroupTwo {

	public static void main(String[] args) {
		SpringApplication.run(BootcampGroupTwo.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}