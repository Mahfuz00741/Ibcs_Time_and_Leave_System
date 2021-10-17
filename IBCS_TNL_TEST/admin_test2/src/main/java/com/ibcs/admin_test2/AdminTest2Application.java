package com.ibcs.admin_test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AdminTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminTest2Application.class, args);
	}

}
