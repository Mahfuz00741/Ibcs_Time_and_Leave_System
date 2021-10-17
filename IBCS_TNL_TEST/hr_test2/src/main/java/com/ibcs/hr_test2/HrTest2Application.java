package com.ibcs.hr_test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HrTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(HrTest2Application.class, args);
	}

}
