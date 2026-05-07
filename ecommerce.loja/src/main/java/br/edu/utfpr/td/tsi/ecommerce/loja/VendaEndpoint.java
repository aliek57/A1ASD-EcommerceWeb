package br.edu.utfpr.td.tsi.ecommerce.loja;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.td.tsi.ecommerce.produtos.Produto;

@RestController
@CrossOrigin
public class VendaEndpoint {
	ConexaoCliente cliente = new ConexaoCliente();
	private static final Logger logger = Logger.getLogger(VendaEndpoint.class.getName());
	
	@GetMapping("/catalogo")
    public String carregarCatalogo() {
        return cliente.buscarCatalogo();
    }
	
	@GetMapping("/cep/{cep}")
	public String consultarCep(@PathVariable String cep) {
	    return cliente.buscarCep(cep);
	}
	
	@PostMapping("/finalizar")
	public Venda finalizarVenda(@RequestBody Venda venda) {
		logger.log(Level.INFO, ">>>>> ORQUESTRAÇÃO DE VENDA <<<<<");
		
		String emailConfirmacao = String.format(
	        "{\"destinatario\":\"%s\", \"assunto\":\"Pedido Recebido\", \"mensagem\":\"Olá %s, seu pedido foi recebido e está aguardando pagamento.\"}",
	        venda.getEmailCliente(), venda.getNomeCliente()
	    );
	    cliente.enviarEmail(emailConfirmacao);
	    logger.log(Level.INFO, "Email enviado: " + emailConfirmacao);
	    
	    String pagamentoJSON = String.format(
	        "{\"numeroCartao\":\"%s\", \"nomeTitular\":\"%s\", \"valor\":%s}",
	        venda.getNumeroCartao(), venda.getNomeCliente(), venda.getValorTotal()
	    );
	    String resultadoPagamento = cliente.processarPagamento(pagamentoJSON);
	    logger.log(Level.INFO, "Resultado pagamento: " + pagamentoJSON);
	    
	    String status = resultadoPagamento.contains("\"status\":\"Aprovado\"") ? "Aprovado" : "Reprovado";
	    venda.setStatusPagamento(status);
	    
	    String emailResultado = String.format(
            "{\"destinatario\":\"%s\", \"assunto\":\"Status do Pagamento\", \"mensagem\":\"Olá %s, seu pedido de valor R$%s no cartão %s foi %s.\"}",
            venda.getEmailCliente(), venda.getNomeCliente(), venda.getValorTotal(), venda.getNumeroCartao(), status
        );
        cliente.enviarEmail(emailResultado);
        
        if (status.equals("Aprovado")) {
        	String nf = java.util.UUID.randomUUID().toString();
        	String dataEmitida = java.time.LocalDate.now().toString();
        	
        	String fiscalJSON = String.format(
    	        "{\"nota\":\"NF-%s\", \"chaveAcesso\":\"110903\", \"dataEmissao\":\"%s\", \"valorTotal\":%s}",
    	        nf, dataEmitida, venda.getValorTotal()
    	    );
        	
        	logger.log(Level.INFO, "Email envio resultado: " + emailResultado);
        	
        	String resultadoFiscal = cliente.emitirNF(fiscalJSON);
            logger.log(Level.INFO, "Nota Fiscal emitida: " + resultadoFiscal);
            
            venda.setNumeroNotaFiscal("NF-" + nf);
            
            for (Produto item : venda.getItens()) {
            	cliente.baixarEstoque(item.getId(), 1);
            }
            
            String emailFiscal = String.format(
                "{\"destinatario\":\"%s\", \"assunto\":\"Envio Nota Fiscal\", \"mensagem\":\"Olá %s, a nota fiscal da sua compra é: %s\"}",
                venda.getEmailCliente(), venda.getNomeCliente(), venda.getNumeroNotaFiscal()
            );
            cliente.enviarEmail(emailFiscal);
            logger.log(Level.INFO, "Email envio NF: " + emailFiscal);
            
            String codigoRastreio = "BR" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            String dataPrevisao = java.time.LocalDate.now().plusDays(7).toString();
            
            String entregaJSON = String.format(
        	    "{\"codigo\":\"%s\", \"dataPrevista\":\"%s\", \"status\":\"Postado\"}",
        	    codigoRastreio, dataPrevisao
        	);
            
            String resultadoEntrega = cliente.agendarEntrega(entregaJSON);
            logger.log(Level.INFO, "Entrega agendada: " + resultadoEntrega);
            
            venda.setProtocoloRastreio(codigoRastreio);
            
            String emailEntrega = String.format(
        	    "{\"destinatario\":\"%s\", \"assunto\":\"Pedido Enviado!\", \"mensagem\":\"Olá %s, o seu pedido foi postado para envio. Código de rastreio: %s. Previsão de entrega: %s\"}",
        	    venda.getEmailCliente(), venda.getNomeCliente(), codigoRastreio, dataPrevisao
        	);
        	cliente.enviarEmail(emailEntrega);
        	logger.log(Level.INFO, "Email de entrega prevista: " + emailEntrega);
        } else {
        	logger.log(Level.SEVERE, "Email envio resultado: " + emailResultado);
        }
	    
	    return venda;
	}
}