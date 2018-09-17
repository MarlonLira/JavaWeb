package br.com.empresa.interfaces;

import java.util.List;

import br.com.empresa.entidade.Equipamento;

public interface IEquipamento {

	public void inserir(Equipamento equipamento);

	public void alterar(Equipamento equipamento);

	public void excluir(Equipamento equipamento);

	public List<Equipamento> listarTodos();

	public Equipamento carregar(Long id);

}
