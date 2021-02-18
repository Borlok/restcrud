package com.borlok.crudrest;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRestApplication.class, args);
	}

}
