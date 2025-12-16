package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;  


import org.springframework.context.annotation.ComponentScan;

import com.example.demo.config.OpenAIConfig;

import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;



@SpringBootApplication(
  exclude = {
                     PersistenceExceptionTranslationAutoConfiguration.class
       
  }
)
@ComponentScan(
      basePackages = "com.example.demo"
      )
@MapperScan("com.example.mapper")
@EnableConfigurationProperties(OpenAIConfig.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
