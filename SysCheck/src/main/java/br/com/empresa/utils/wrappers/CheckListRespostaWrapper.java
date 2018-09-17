package br.com.empresa.utils.wrappers;

import java.io.Serializable;
import java.util.List;

public class CheckListRespostaWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String checkList;
	private String equipamento;
	private String data;
	private String usuario;
	private List<ItemRespostaWrapper> itens;

	/*
	 * Getters e Setters
	 */

	public String getCheckList() {
		return checkList;
	}

	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<ItemRespostaWrapper> getItens() {
		return itens;
	}

	public void setItens(List<ItemRespostaWrapper> itens) {
		this.itens = itens;
	}

}
