package br.edu.utfpr.td.tsi.ecommerce.fiscal;

import java.math.BigDecimal;

public class Fiscal {
	private String nota;
	private String chaveAcesso;
	private String dataEmissao;
	private BigDecimal valorTotal;
	
	public Fiscal() {}
	
	public Fiscal(String nota, String chaveAcesso, String dataEmissao, BigDecimal valorTotal) {
		super();
		this.nota = nota;
		this.chaveAcesso = chaveAcesso;
		this.dataEmissao = dataEmissao;
		this.valorTotal = valorTotal;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
