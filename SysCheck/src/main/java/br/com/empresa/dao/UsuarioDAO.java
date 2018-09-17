package br.com.empresa.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.empresa.entidade.Usuario;
import br.com.empresa.interfaces.IUsuario;
import br.com.empresa.utils.Utils;

public class UsuarioDAO implements IUsuario {

	private Session session;

	@Override
	public void inserir(Usuario usuario) {
		usuario.setTxtSenha(Utils.criptografaSenha(usuario.getTxtSenha()));
		getSession().save(usuario);
	}

	@Override
	public void alterar(Usuario usuario) {
		Criteria ct = getSession().createCriteria(Usuario.class);

		ct.add(Restrictions.eq(Usuario.strId, usuario.getId()));

		Usuario usuarioOld = (Usuario) ct.uniqueResult();

		if (!usuarioOld.getTxtSenha().equals(usuario.getTxtSenha())) {
			usuario.setTxtSenha(Utils.criptografaSenha(usuario.getTxtSenha()));
		}

		getSession().evict(usuarioOld);

		getSession().update(usuario);
	}

	@Override
	public void excluir(Usuario usuario) {
		usuario.setDthExclusao(new Timestamp(new Date().getTime()));
		getSession().update(usuario);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarTodos() {
		Criteria ct = getSession().createCriteria(Usuario.class);

		ct.createAlias(Usuario.strTipoUsuario, Usuario.strTipoUsuario);

		ct.add(Restrictions.isNull(Usuario.strDthExclusao));
		ct.add(Restrictions.not(Restrictions.like(Usuario.strNomUsuario, Utils.USUARIO_MASTER)));

		ct.addOrder(Order.asc(Usuario.strId));

		return ct.list();
	}

	@Override
	public Usuario carregar(Long id) {
		return (Usuario) getSession().get(Usuario.class, id);
	}

	@Override
	public Usuario carregarPorLogin(String nomUsuario) {
		Criteria ct = getSession().createCriteria(Usuario.class);

		ct.add(Restrictions.eq(Usuario.strNomUsuario, nomUsuario.trim()));

		ct.setMaxResults(1);

		return (Usuario) ct.uniqueResult();
	}

	@Override
	public Usuario findByLogin(String login, String senha) {
		Criteria ct = getSession().createCriteria(Usuario.class);

		ct.createAlias(Usuario.strTipoUsuario, Usuario.strTipoUsuario);

		ct.add(Restrictions.eq(Usuario.strNomUsuario, login));
		ct.add(Restrictions.eq(Usuario.strTxtSenha, Utils.criptografaSenha(senha)));
		ct.add(Restrictions.isNull(Usuario.strDthExclusao));

		return (Usuario) ct.uniqueResult();
	}

	/*
	 * Getters e Setters
	 */

	public void setSession(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

}
