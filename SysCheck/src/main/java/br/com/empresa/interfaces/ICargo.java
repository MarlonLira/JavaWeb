package br.com.empresa.interfaces;

import java.util.List;

import br.com.empresa.entidade.Cargo;

public interface ICargo {

	public void inserir(Cargo cargo);

	public void alterar(Cargo cargo);

	public void excluir(Cargo cargo);

	public List<Cargo> listarTodos();

	public Cargo carregar(Long id);

}
