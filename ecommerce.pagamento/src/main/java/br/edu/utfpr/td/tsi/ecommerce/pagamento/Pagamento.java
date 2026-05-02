package br.edu.utfpr.td.tsi.ecommerce.pagamento;

import java.math.BigDecimal;

public class Pagamento {
	private String numeroCartao;
	private String nomeTitular;
	private BigDecimal valor;
	private String status;
	
	public Pagamento() {}
	
	public Pagamento(String numeroCartao, String nomeTitular, BigDecimal valor, String status) {
		super();
		this.numeroCartao = numeroCartao;
		this.nomeTitular = nomeTitular;
		this.valor = valor;
		this.status = status;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
