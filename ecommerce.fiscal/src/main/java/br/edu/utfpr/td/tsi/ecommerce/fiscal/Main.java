package br.edu.utfpr.td.tsi.ecommerce.fiscal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/ecommerce.fiscal");
		SpringApplication.run(Main.class, args);
	}
}
