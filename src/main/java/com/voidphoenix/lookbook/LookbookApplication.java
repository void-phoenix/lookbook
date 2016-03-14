package com.voidphoenix.lookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LookbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(LookbookApplication.class, args);
	}
}
