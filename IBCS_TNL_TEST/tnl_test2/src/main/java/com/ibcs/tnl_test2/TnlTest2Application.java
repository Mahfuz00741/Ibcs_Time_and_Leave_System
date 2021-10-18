package com.ibcs.tnl_test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class TnlTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(TnlTest2Application.class, args);
	}

}
