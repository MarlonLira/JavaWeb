package br.com.empresa.rn;

import java.util.List;

import br.com.empresa.dao.CheckListRespostaDAO;
import br.com.empresa.dao.DAOFactory;
import br.com.empresa.entidade.CheckListResposta;

public class CheckListRespostaRN {

	private CheckListRespostaDAO checkListRespostaDAO = new CheckListRespostaDAO();

	/*
	 * Construtor
	 */

	public CheckListRespostaRN() {
		setCheckListRespostaDAO((CheckListRespostaDAO) DAOFactory.novoCheckListResposta());
	}

	/*
	 * Especificos
	 */

	public void inserir(CheckListResposta checkListResposta) throws Exception {
		checaRestricoes(checkListResposta);

		if (checkListResposta.getId() == null) {
			getCheckListRespostaDAO().inserir(checkListResposta);
		} else {
			getCheckListRespostaDAO().alterar(checkListResposta);
		}
	}

	public void excluir(CheckListResposta checkListResposta) {
		getCheckListRespostaDAO().excluir(checkListResposta);
	}

	public List<CheckListResposta> listarTodos() {
		return getCheckListRespostaDAO().listarTodos();
	}

	public CheckListResposta carregar(Long id) {
		return getCheckListRespostaDAO().carregar(id);
	}

	private void checaRestricoes(CheckListResposta checkListResposta) throws Exception {

	}

	/*
	 * Getters e Setters
	 */

	public CheckListRespostaDAO getCheckListRespostaDAO() {
		return checkListRespostaDAO;
	}

	public void setCheckListRespostaDAO(CheckListRespostaDAO checkListRespostaDAO) {
		this.checkListRespostaDAO = checkListRespostaDAO;
	}

}
