package br.edu.utfpr.td.tsi.ecommerce.loja;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class VendaEndpoint {
	@GetMapping("/catalogo")
    public String carregarCatalogo() {
        ConexaoCliente cliente = new ConexaoCliente();
        return cliente.buscarCatalogo();
    }
}