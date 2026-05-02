package br.edu.utfpr.td.tsi.ecommerce.loja;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class VendaEndpoint {

    @PostMapping("/finalizar")
    public Venda finalizarCompra(@RequestBody Venda venda) {
        return venda;
    }
}