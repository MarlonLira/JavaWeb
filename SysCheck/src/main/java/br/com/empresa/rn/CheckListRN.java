package br.com.empresa.rn;

import java.util.List;

import br.com.empresa.dao.CheckListDAO;
import br.com.empresa.dao.DAOFactory;
import br.com.empresa.entidade.CheckList;

public class CheckListRN {

	private CheckListDAO checkListDAO = new CheckListDAO();

	/*
	 * Construtor
	 */

	public CheckListRN() {
		setCheckListDAO((CheckListDAO) DAOFactory.novoCheckList());
	}

	/*
	 * Especificos
	 */

	public void inserir(CheckList checkList) throws Exception {
		checaRestricoes(checkList);

		if (checkList.getId() == null) {
			getCheckListDAO().inserir(checkList);
		} else {
			getCheckListDAO().alterar(checkList);
		}
	}

	public void excluir(CheckList checkList) {
		getCheckListDAO().excluir(checkList);
	}

	public List<CheckList> listarTodos() {
		return getCheckListDAO().listarTodos();
	}

	public CheckList carregar(Long id) {
		return getCheckListDAO().carregar(id);
	}

	private void checaRestricoes(CheckList checkList) throws Exception {

	}

	/*
	 * Getters e Setters
	 */

	public CheckListDAO getCheckListDAO() {
		return checkListDAO;
	}

	public void setCheckListDAO(CheckListDAO checkListDAO) {
		this.checkListDAO = checkListDAO;
	}

}
