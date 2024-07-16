package ru.aquamarina.saleadviser.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.aquamarina.saleadviser.repository")
public class SaleadviserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleadviserApplication.class, args);
	}

}
