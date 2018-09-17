package br.com.empresa.interfaces;

import java.util.List;

import br.com.empresa.entidade.Usuario;

public interface IUsuario {

	public void inserir(Usuario usuario);

	public void alterar(Usuario usuario);

	public void excluir(Usuario usuario);

	public List<Usuario> listarTodos();

	public Usuario carregar(Long id);

	public Usuario carregarPorLogin(String nomUsuario);

	public Usuario findByLogin(String login, String senha);

}
