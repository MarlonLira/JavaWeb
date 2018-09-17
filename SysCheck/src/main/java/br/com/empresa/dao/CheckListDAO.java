package br.com.empresa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.empresa.entidade.CheckList;
import br.com.empresa.interfaces.ICheckList;

public class CheckListDAO implements ICheckList {

	private Session session;

	/*
	 * Overriders
	 */

	@Override
	public void inserir(CheckList checkList) {
		getSession().save(checkList);
	}

	@Override
	public void alterar(CheckList checkList) {
		getSession().update(checkList);
	}

	@Override
	public void excluir(CheckList checkList) {
		checkList.setDthExclusao(new Date());
		getSession().update(checkList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckList> listarTodos() {
		Criteria ct = getSession().createCriteria(CheckList.class);

		ct.add(Restrictions.isNull(CheckList.strDthExclusao));

		return ct.list();
	}

	@Override
	public CheckList carregar(Long id) {
		return (CheckList) getSession().get(CheckList.class, id);
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
