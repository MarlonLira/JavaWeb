package br.com.empresa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.empresa.entidade.Cargo;
import br.com.empresa.interfaces.ICargo;

public class CargoDAO implements ICargo {

	private Session session;

	@Override
	public void inserir(Cargo cargo) {
		getSession().save(cargo);
	}

	@Override
	public void alterar(Cargo cargo) {
		getSession().update(cargo);
	}

	@Override
	public void excluir(Cargo cargo) {
		cargo.setDthExclusao(new Date());
		getSession().update(cargo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cargo> listarTodos() {
		Criteria ct = getSession().createCriteria(Cargo.class);

		ct.add(Restrictions.isNull(Cargo.strDthExclusao));

		return ct.list();
	}

	@Override
	public Cargo carregar(Long id) {
		return (Cargo) getSession().get(Cargo.class, id);
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
