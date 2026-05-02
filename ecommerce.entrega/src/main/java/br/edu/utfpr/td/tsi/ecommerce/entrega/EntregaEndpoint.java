package br.edu.utfpr.td.tsi.ecommerce.entrega;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EntregaEndpoint {

    @PostMapping(value = "/entrega", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Entrega agendarEntrega(@RequestBody Entrega dados) {
        return dados;
    }
}