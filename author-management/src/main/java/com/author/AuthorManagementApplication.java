package com.author;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthorManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorManagementApplication.class, args);
	}

}
