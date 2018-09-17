package br.com.empresa.interfaces;

import java.util.List;

import br.com.empresa.entidade.CheckList;
import br.com.empresa.entidade.CheckListItem;

public interface ICheckListItem {

	public void inserir(CheckListItem checkListItem);

	public void alterar(CheckListItem checkListItem);

	public void excluir(CheckListItem checkListItem);

	public List<CheckListItem> listarTodos();

	public CheckListItem carregar(Long id);

	public List<CheckListItem> carregarPorCheckList(CheckList checkList);

}
