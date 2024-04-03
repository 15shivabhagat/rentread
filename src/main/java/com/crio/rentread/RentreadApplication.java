package com.crio.rentread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class RentreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentreadApplication.class, args);
	}

	// @Bean
	// public ModelMapper

}
