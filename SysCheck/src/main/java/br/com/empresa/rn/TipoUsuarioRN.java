package br.com.empresa.rn;

import java.util.List;

import br.com.empresa.dao.DAOFactory;
import br.com.empresa.dao.TipoUsuarioDAO;
import br.com.empresa.entidade.TipoUsuario;

public class TipoUsuarioRN {

	private TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();

	/*
	 * Construtor
	 */

	public TipoUsuarioRN() {
		setTipoUsuarioDAO((TipoUsuarioDAO) DAOFactory.novoTipoUsuario());
	}

	public void inserir(TipoUsuario tipoUsuario) throws Exception {
		checaRestricoes(tipoUsuario);

		if (tipoUsuario.getId() == null) {
			getTipoUsuarioDAO().inserir(tipoUsuario);
		} else {
			getTipoUsuarioDAO().alterar(tipoUsuario);
		}
	}

	public void excluir(TipoUsuario tipoUsuario) {
		getTipoUsuarioDAO().excluir(tipoUsuario);
	}

	public List<TipoUsuario> listarTodos() {
		return getTipoUsuarioDAO().listarTodos();
	}

	public TipoUsuario carregar(Long id) {
		return getTipoUsuarioDAO().carregar(id);
	}

	public TipoUsuario carregarPorTipo(String nomTipo) {
		return getTipoUsuarioDAO().carregarPorTipo(nomTipo);
	}

	private void checaRestricoes(TipoUsuario tipoUsuario) throws Exception {

	}

	/*
	 * Getters e Setters
	 */

	public TipoUsuarioDAO getTipoUsuarioDAO() {
		return tipoUsuarioDAO;
	}

	public void setTipoUsuarioDAO(TipoUsuarioDAO tipoUsuarioDAO) {
		this.tipoUsuarioDAO = tipoUsuarioDAO;
	}

}
