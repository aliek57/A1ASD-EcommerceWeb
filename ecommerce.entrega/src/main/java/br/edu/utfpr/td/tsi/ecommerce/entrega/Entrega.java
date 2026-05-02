package br.edu.utfpr.td.tsi.ecommerce.entrega;

public class Entrega {
	private String codigo;
	private String dataPrevista;
	private String status;
	
	public Entrega() {}
	
	public Entrega(String codigo, String dataPrevista, String status) {
		super();
		this.codigo = codigo;
		this.dataPrevista = dataPrevista;
		this.status = status;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
