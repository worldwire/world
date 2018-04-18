package com.spring.worldwire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages="com.spring.worldwire")
//@MapperScan(basePackages="com.spring.worldwire.dao")
public class Application {
	public static void main(String[] args) {
		 SpringApplication.run(Application.class, args);
	}

}
