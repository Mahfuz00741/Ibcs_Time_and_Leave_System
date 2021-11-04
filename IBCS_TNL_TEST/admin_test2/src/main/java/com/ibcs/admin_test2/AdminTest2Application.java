package com.ibcs.admin_test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableEurekaClient
@SpringBootApplication
public class AdminTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminTest2Application.class, args);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
