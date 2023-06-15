package com.nhnacademy.springbootaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
//@ConfigurationPropertiesScan
public class SpringBootAccountApplication {

	public static void main(String[] args) {

//		SpringApplication.run(SpringBootAccountApplication.class, args);
		SpringApplication application = new SpringApplication(SpringBootAccountApplication.class);
		//application.setBanner((environment, sourceClass, out) ->{});
		application.run(args);
	}

}
