package ru.ds.education.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CbrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbrApplication.class, args);
	}

}
