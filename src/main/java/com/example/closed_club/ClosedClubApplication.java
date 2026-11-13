package com.example.closed_club;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.closed_club")
public class ClosedClubApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClosedClubApplication.class, args);
	}
}