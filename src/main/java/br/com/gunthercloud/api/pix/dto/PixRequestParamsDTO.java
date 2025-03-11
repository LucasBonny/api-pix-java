package br.com.gunthercloud.api.pix.dto;

public class PixRequestParamsDTO {

	private int expiracao;
	private String valor;
	private String chave;
	
	public PixRequestParamsDTO() {

	}

	public PixRequestParamsDTO(int expiracao, String valor, String chave) {
		this.expiracao = expiracao;
		this.valor = valor;
		this.chave = chave;
	}

	public int getExpiracao() {
		return expiracao;
	}

	public void setExpiracao(int expiracao) {
		this.expiracao = expiracao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
}
