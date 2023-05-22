package com.bootcamp.Assignment;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication()
@EnableJpaRepositories(basePackages = "com.bootcamp.Assignment.repositories")
public class BootcampGroupTwo {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		// Set the environment variables
		dotenv.entries().forEach(entry -> {
			String key = entry.getKey();
			String value = entry.getValue();
			System.setProperty(key, value);
		});

		SpringApplication.run(BootcampGroupTwo.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}