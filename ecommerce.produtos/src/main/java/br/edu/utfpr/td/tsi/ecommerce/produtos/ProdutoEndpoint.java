package br.edu.utfpr.td.tsi.ecommerce.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProdutoEndpoint {

	@GetMapping(value = "/catalogo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> carregarCatalogo() {
		List<Produto> produtos = criarProdutos();
		return ResponseEntity.status(HttpStatus.OK).body(produtos);
	}

	private List<Produto> criarProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();

		Produto notebook = new Produto("p001", "Notebook Dell", "https://cdn.awsli.com.br/600x450/2179/2179851/produto/346101469/notebook-dell-vostro-3401--8--6bdzezwqu6.png", new BigDecimal("3500.00"), 15);
		produtos.add(notebook);

		return produtos;
	}

}
