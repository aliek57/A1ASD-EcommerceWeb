package br.edu.utfpr.td.tsi.ecommerce.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/ecommerce.pagamento");
		SpringApplication.run(Main.class, args);
	}
}
