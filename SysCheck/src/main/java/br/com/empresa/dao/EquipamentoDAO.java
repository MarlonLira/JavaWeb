package br.com.empresa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.com.empresa.entidade.Equipamento;
import br.com.empresa.interfaces.IEquipamento;

public class EquipamentoDAO implements IEquipamento {

	private Session session;

	@Override
	public void inserir(Equipamento equipamento) {
		getSession().save(equipamento);
	}

	@Override
	public void alterar(Equipamento equipamento) {
		getSession().update(equipamento);
	}

	@Override
	public void excluir(Equipamento equipamento) {
		equipamento.setDthExclusao(new Date());
		getSession().update(equipamento);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipamento> listarTodos() {
		Criteria ct = getSession().createCriteria(Equipamento.class);

		ct.createAlias(Equipamento.strCheckList, Equipamento.strCheckList, JoinType.LEFT_OUTER_JOIN);

		ct.add(Restrictions.isNull(Equipamento.strDthExclusao));

		return ct.list();
	}

	@Override
	public Equipamento carregar(Long id) {
		return (Equipamento) getSession().get(Equipamento.class, id);
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
