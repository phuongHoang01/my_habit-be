package com.myhabit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.myhabit")
@EnableWebSecurity
public class MyHabitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHabitApplication.class, args);
	}

}
