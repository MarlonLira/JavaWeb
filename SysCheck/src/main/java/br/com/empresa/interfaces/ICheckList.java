package br.com.empresa.interfaces;

import java.util.List;

import br.com.empresa.entidade.CheckList;

public interface ICheckList {

	public void inserir(CheckList checkList);

	public void alterar(CheckList checkList);

	public void excluir(CheckList checkList);

	public List<CheckList> listarTodos();

	public CheckList carregar(Long id);

}
