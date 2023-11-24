package com.workshop.solid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolidApplication implements CommandLineRunner {
	
	@Autowired
	private Menu menu;

	public static void main(String[] args) {
		SpringApplication.run(SolidApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		menu.ejecutar();
	}

}
