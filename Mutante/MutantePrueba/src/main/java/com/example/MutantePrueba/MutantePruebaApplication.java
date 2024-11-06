package com.example.MutantePrueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.MutantePrueba.entity")
@EnableJpaRepositories(basePackages = "com.example.MutantePrueba.repositories")
public class MutantePruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantePruebaApplication.class, args);
	}
}