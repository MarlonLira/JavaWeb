package br.com.empresa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.empresa.entidade.CheckList;
import br.com.empresa.entidade.CheckListItem;
import br.com.empresa.interfaces.ICheckListItem;

public class CheckListItemDAO implements ICheckListItem {

	private Session session;

	@Override
	public void inserir(CheckListItem checkListItem) {
		getSession().save(checkListItem);
	}

	@Override
	public void alterar(CheckListItem checkListItem) {
		getSession().update(checkListItem);
	}

	@Override
	public void excluir(CheckListItem checkListItem) {
		checkListItem.setDthExclusao(new Date());
		getSession().update(checkListItem);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckListItem> listarTodos() {
		Criteria ct = getSession().createCriteria(CheckListItem.class);

		ct.add(Restrictions.isNull(CheckListItem.strDthExclusao));

		return ct.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckListItem> carregarPorCheckList(CheckList checkList) {
		Criteria ct = getSession().createCriteria(CheckListItem.class);

		ct.createAlias(CheckListItem.strCheckList, CheckListItem.strCheckList);
		ct.createAlias(CheckListItem.strItem, CheckListItem.strItem);

		ct.add(Restrictions.isNull(CheckListItem.strDthExclusao));
		ct.add(Restrictions.eq(CheckListItem.strCheckList, checkList));

		return ct.list();
	}

	@Override
	public CheckListItem carregar(Long id) {
		return (CheckListItem) getSession().get(CheckListItem.class, id);
	}

	/*
	 * Getters e Setters
	 */

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
