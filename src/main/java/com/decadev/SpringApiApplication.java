package com.decadev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApiApplication {

	public static void main(String[] args) {
		// Create an instance of SpringApplication
		SpringApplication app = new SpringApplication(SpringApiApplication.class);

		// Set additional profiles
		app.setAdditionalProfiles("dev");

		// Run the application
		app.run(args);
	}

}
