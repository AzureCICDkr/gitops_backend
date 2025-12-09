package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;  


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;



@SpringBootApplication(
  exclude = {
                     PersistenceExceptionTranslationAutoConfiguration.class
       
  }
)
@ComponentScan(
      basePackages = "com.example.demo"
      )
@MapperScan("com.example.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
