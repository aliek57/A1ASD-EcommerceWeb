package br.edu.utfpr.td.tsi.ecommerce.produtos;

import java.math.BigDecimal;

public class Produto {

	private String id;
	private String nome;
	private String urlImagem;
	private BigDecimal preco;
	private int quantidadeEmEstoque;
	
	public Produto() {}

	public Produto(String id, String nome, String urlImagem, BigDecimal preco, int quantidadeEmEstoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.urlImagem = urlImagem;
		this.preco = preco;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public Produto id(String id) {
		this.id = id;
		return this;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

}
