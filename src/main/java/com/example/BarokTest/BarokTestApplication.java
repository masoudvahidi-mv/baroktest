package com.example.BarokTest;

import com.example.BarokTest.service.LogActivityTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class BarokTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarokTestApplication.class, args);
	}
	}
