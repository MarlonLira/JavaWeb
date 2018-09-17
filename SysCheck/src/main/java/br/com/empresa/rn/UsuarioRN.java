package br.com.empresa.rn;

import java.util.List;

import br.com.empresa.dao.DAOFactory;
import br.com.empresa.dao.UsuarioDAO;
import br.com.empresa.entidade.Usuario;
import br.com.empresa.exceptions.RestricaoPersonalizada;
import br.com.empresa.utils.Utils;

public class UsuarioRN {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public UsuarioRN() {
		this.setUsuarioDAO((UsuarioDAO) DAOFactory.novoUsuario());
	}

	public void inserir(Usuario usuario) throws Exception {
		checaRestricoes(usuario);

		if (usuario.getId() == null) {
			getUsuarioDAO().inserir(usuario);
		} else {
			getUsuarioDAO().alterar(usuario);
		}
	}

	public void excluir(Usuario usuario) {
		getUsuarioDAO().excluir(usuario);
	}

	public List<Usuario> listarTodos() {
		return getUsuarioDAO().listarTodos();
	}

	public Usuario findByLogin(String login, String senha) {
		return getUsuarioDAO().findByLogin(login, senha);
	}

	public Usuario carregar(Long id) {
		return getUsuarioDAO().carregar(id);
	}

	private boolean verificaUsuarioJaCadastrado(String nomUsuario) {
		return getUsuarioDAO().carregarPorLogin(nomUsuario.trim()) != null;
	}

	private void checaRestricoes(Usuario usuario) throws Exception {
		if (usuario.getNomUsuario() == null || usuario.getNomUsuario().trim().isEmpty()) {
			throw new RestricaoPersonalizada("Favor informar o nome do usuário");
		}

		if (verificaUsuarioJaCadastrado(usuario.getNomUsuario()) && usuario.getId() == null) {
			throw new RestricaoPersonalizada("Já existem um usuário utilizando esse login, favor utilizar um login diferente.");
		}

		if (usuario.getIndRecebeEmail() != null && usuario.getIndRecebeEmail().equals(Utils.SIM)) {
			if (usuario.getTxtEmail() == null || usuario.getTxtEmail().trim().isEmpty()) {
				throw new RestricaoPersonalizada("Favor informar o email que receberá as notificações.");
			}
		}

		if (usuario.getTxtSenha() == null || usuario.getTxtSenha().trim().isEmpty()) {
			throw new RestricaoPersonalizada("Favor informar a senha que será utilizada.");
		}

		if (usuario.getTipoUsuario() == null) {
			throw new RestricaoPersonalizada("Favor informar o tipo do usuário.");
		}

		if (usuario.getNumMatricula() == null || usuario.getNumMatricula().trim().isEmpty()) {
			throw new RestricaoPersonalizada("Favor informar o número da matricula do funcionário.");
		}
	}

	/*
	 * Getters e Setters
	 */

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
