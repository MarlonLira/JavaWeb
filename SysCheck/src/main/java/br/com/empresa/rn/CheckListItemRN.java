package br.com.empresa.rn;

import java.util.List;

import br.com.empresa.dao.CheckListItemDAO;
import br.com.empresa.dao.DAOFactory;
import br.com.empresa.entidade.CheckList;
import br.com.empresa.entidade.CheckListItem;

public class CheckListItemRN {

	private CheckListItemDAO checkListItemDAO = new CheckListItemDAO();

	/*
	 * Construtor
	 */

	public CheckListItemRN() {
		setCheckListItemDAO((CheckListItemDAO) DAOFactory.novoCheckListItem());
	}

	/*
	 * Especificos
	 */

	public void inserir(CheckListItem checkListItem) throws Exception {
		checaRestricoes(checkListItem);

		if (checkListItem.getId() == null) {
			getCheckListItemDAO().inserir(checkListItem);
		} else {
			getCheckListItemDAO().alterar(checkListItem);
		}
	}

	public void excluir(CheckListItem checkListItem) {
		getCheckListItemDAO().excluir(checkListItem);
	}

	public List<CheckListItem> listarTodos() {
		return getCheckListItemDAO().listarTodos();
	}

	public List<CheckListItem> carregarPorCheckList(CheckList checkList) {
		return getCheckListItemDAO().carregarPorCheckList(checkList);
	}

	public CheckListItem carregar(Long id) {
		return getCheckListItemDAO().carregar(id);
	}

	private void checaRestricoes(CheckListItem checkListItem) throws Exception {

	}

	/*
	 * Getters e Setters
	 */

	public CheckListItemDAO getCheckListItemDAO() {
		return checkListItemDAO;
	}

	public void setCheckListItemDAO(CheckListItemDAO checkListItemDAO) {
		this.checkListItemDAO = checkListItemDAO;
	}

}
