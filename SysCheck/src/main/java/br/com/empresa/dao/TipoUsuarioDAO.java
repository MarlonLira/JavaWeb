package br.com.empresa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.empresa.entidade.TipoUsuario;
import br.com.empresa.interfaces.ITipoUsuario;

public class TipoUsuarioDAO implements ITipoUsuario {

	private Session session;

	@Override
	public void inserir(TipoUsuario tipoUsuario) {
		getSession().save(tipoUsuario);
	}

	@Override
	public void alterar(TipoUsuario tipoUsuario) {

	}

	@Override
	public void excluir(TipoUsuario tipoUsuario) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoUsuario> listarTodos() {
		Criteria ct = getSession().createCriteria(TipoUsuario.class);

		ct.add(Restrictions.ne(TipoUsuario.strSglTipoUsuario, TipoUsuario.TIPO_MASTER));

		return ct.list();
	}

	@Override
	public TipoUsuario carregar(Long id) {
		return null;
	}

	@Override
	public TipoUsuario carregarPorTipo(String nomTipo) {
		Criteria ct = getSession().createCriteria(TipoUsuario.class);

		ct.add(Restrictions.eq(TipoUsuario.strSglTipoUsuario, nomTipo));

		return (TipoUsuario) ct.uniqueResult();
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
