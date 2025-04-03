package com.techm.mobileprepaidrechargesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.techm.mobileprepaidrechargesystem.repository")
public class MobilePrepaidRechargeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilePrepaidRechargeApplication.class, args);
	}

}
