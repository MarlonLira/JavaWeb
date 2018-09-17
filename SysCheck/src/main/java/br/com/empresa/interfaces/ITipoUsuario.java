package br.com.empresa.interfaces;

import java.util.List;

import br.com.empresa.entidade.TipoUsuario;

public interface ITipoUsuario {

	public void inserir(TipoUsuario tipoUsuario);

	public void alterar(TipoUsuario tipoUsuario);

	public void excluir(TipoUsuario tipoUsuario);

	public List<TipoUsuario> listarTodos();

	public TipoUsuario carregar(Long id);

	public TipoUsuario carregarPorTipo(String nomTipo);
	
}
