package br.edu.utfpr.td.tsi.ecommerce.fiscal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FiscalEndpoint {

    @PostMapping(value = "/fiscal", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fiscal emitirNota(@RequestBody Fiscal dados) {
        return dados;
    }
}