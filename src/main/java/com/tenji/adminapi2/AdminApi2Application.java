package com.tenji.adminapi2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tenji.adminapi2.mapper")
public class AdminApi2Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminApi2Application.class, args);
	}

}
