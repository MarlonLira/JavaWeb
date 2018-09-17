package br.com.empresa.utils.wrappers;

public class ItemRespostaWrapper {
	private String item;
	private String status;
	private String observacao;

	/*
	 * Getters e Setters
	 */

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusFormatado() {
		return getStatus().equals("O") ? "Ok" : "Problema";
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
