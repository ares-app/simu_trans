package org.ares.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StartApp{
	public static void main(String[] args) {
		SpringApplication.run(StartApp.class, args);
	}
}
