package ru.aquamarina.saleadviser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.anorisno.appDataTool.repository")
public class SaleadviserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleadviserApplication.class, args);
	}

}
