package br.edu.utfpr.td.tsi.ecommerce.loja;

import java.math.BigDecimal;
import java.util.List;
import br.edu.utfpr.td.tsi.ecommerce.produtos.Produto;

public class Venda {
    private String nomeCliente;
    private String emailCliente;
    private String cep;
    private String numeroCartao;
    private List<Produto> itens;
    private BigDecimal valorTotal;
    private String statusPagamento;
    private String protocoloRastreio;
    private String numeroNotaFiscal;

    public Venda() {}

	public Venda(String nomeCliente, String emailCliente, String cep, String numeroCartao, List<Produto> itens,
			BigDecimal valorTotal, String statusPagamento, String protocoloRastreio, String numeroNotaFiscal) {
		super();
		this.nomeCliente = nomeCliente;
		this.emailCliente = emailCliente;
		this.cep = cep;
		this.numeroCartao = numeroCartao;
		this.itens = itens;
		this.valorTotal = valorTotal;
		this.statusPagamento = statusPagamento;
		this.protocoloRastreio = protocoloRastreio;
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public String getProtocoloRastreio() {
		return protocoloRastreio;
	}

	public void setProtocoloRastreio(String protocoloRastreio) {
		this.protocoloRastreio = protocoloRastreio;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}   
}