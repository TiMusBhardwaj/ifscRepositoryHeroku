package com.example.ifsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author sumit.bhardwaj
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude= {SecurityAutoConfiguration.class})
public class IfscRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfscRepositoryApplication.class, args);
	}

}
