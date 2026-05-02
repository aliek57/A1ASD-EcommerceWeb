package br.edu.utfpr.td.tsi.ecommerce.cep;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CepEndpoint {

	@GetMapping(value = "/cep/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cep buscarEndereco(@PathVariable String cep) {
		Cep c = new Cep(cep, "Rua Cristo Rei", "Vila Becker", "Toledo", "PR");
		return c;
	}
}
