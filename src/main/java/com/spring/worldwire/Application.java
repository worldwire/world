package com.spring.worldwire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.spring.worldwire")
public class Application {
	public static void main(String[] args) {
		 SpringApplication.run(Application.class, args);
	}

}
