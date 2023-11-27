package com.denizkpln.transactionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransactionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServerApplication.class, args);
	}

}
