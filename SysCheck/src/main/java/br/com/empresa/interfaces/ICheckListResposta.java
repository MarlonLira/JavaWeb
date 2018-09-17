package br.com.empresa.interfaces;

import java.util.List;

import br.com.empresa.entidade.CheckListResposta;

public interface ICheckListResposta {

	public void inserir(CheckListResposta checkListResposta);

	public void alterar(CheckListResposta checkListResposta);

	public void excluir(CheckListResposta checkListResposta);

	public List<CheckListResposta> listarTodos();

	public CheckListResposta carregar(Long id);

}
