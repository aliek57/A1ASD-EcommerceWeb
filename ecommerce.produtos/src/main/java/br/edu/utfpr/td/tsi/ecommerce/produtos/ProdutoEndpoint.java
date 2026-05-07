package br.edu.utfpr.td.tsi.ecommerce.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProdutoEndpoint {
	private static List<Produto> produtos = criarProdutos();

	private static List<Produto> criarProdutos() {
		List<Produto> lista = new ArrayList<>();

		lista.add(new Produto("p001", "Notebook Dell", "https://cdn.awsli.com.br/600x450/2179/2179851/produto/346101469/notebook-dell-vostro-3401--8--6bdzezwqu6.png", new BigDecimal("3500.00"), 15));
		lista.add(new Produto("p002", "Console PlayStation 5", "https://images2.kabum.com.br/produtos/fotos/989702/console-sony-playstation-5-ssd-825gb-controle-sem-fio-dualsense-2-jogos-digitais-edicao-digital_1765487654_gg.jpg", new BigDecimal("4200.00"), 50));
	    lista.add(new Produto("p003", "Monitor Gamer", "https://images1.kabum.com.br/produtos/fotos/952751/monitor-gamer-curvo-asus-tuf-34-wqhd-250hz-0-5ms-fast-va-freesync-premium-altura-ajustavel-som-integrado-preto-vg34wqml5a_1772549128_gg.jpg", new BigDecimal("510.00"), 20));
		
		return lista;
	}
	
	@GetMapping(value = "/catalogo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> carregarCatalogo() {
		return ResponseEntity.status(HttpStatus.OK).body(produtos);
	}
	
	@PostMapping("/baixar-estoque/{id}/{qtd}")
    public ResponseEntity<?> baixarEstoque(@PathVariable String id, @PathVariable int qtd) {
        for (Produto p : produtos) {
            if (p.getId().equals(id)) {
                p.setQuantidadeEmEstoque(p.getQuantidadeEmEstoque() - qtd);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
