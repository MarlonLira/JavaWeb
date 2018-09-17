package br.com.empresa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.empresa.entidade.Item;
import br.com.empresa.interfaces.IItem;

public class ItemDAO implements IItem {

	private Session session;

	@Override
	public void inserir(Item item) {
		getSession().save(item);
	}

	@Override
	public void alterar(Item item) {
		getSession().update(item);
	}

	@Override
	public void excluir(Item item) {
		getSession().delete(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> listarTodos() {
		return getSession().createCriteria(Item.class).list();
	}

	@Override
	public Item carregar(Long id) {
		return (Item) getSession().get(Item.class, id);
	}

	@Override
	public Item carregarPorNome(String nomItem) {
		Criteria ct = getSession().createCriteria(Item.class);

		ct.add(Restrictions.eq(Item.strNomItem, nomItem.trim()));

		ct.setMaxResults(1);

		return (Item) ct.uniqueResult();
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
