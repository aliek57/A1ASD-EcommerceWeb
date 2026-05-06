package br.edu.utfpr.td.tsi.ecommerce.pagamento;

import java.math.BigDecimal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PagamentoEndpoint {

	@PostMapping(value = "/pagamento", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Pagamento processarPagamento(@RequestBody Pagamento dados) {
		BigDecimal limiteCartao = new BigDecimal("10000.00");
		
		if (dados.getValor().compareTo(limiteCartao) <= 0) {
			dados.setStatus("Aprovado");
		} else {
			dados.setStatus("Reprovado");
		}

		return dados;
	}
}
