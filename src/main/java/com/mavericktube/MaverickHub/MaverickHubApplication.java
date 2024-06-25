package com.mavericktube.MaverickHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaverickHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaverickHubApplication.class, args);
	}

	// anonymous method.
    static {
		System.out.println("Hello");
	}
}
