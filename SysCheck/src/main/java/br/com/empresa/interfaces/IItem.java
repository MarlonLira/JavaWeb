package br.com.empresa.interfaces;

import java.util.List;

import br.com.empresa.entidade.Item;

public interface IItem {

	public void inserir(Item item);

	public void alterar(Item item);

	public void excluir(Item item);

	public List<Item> listarTodos();

	public Item carregar(Long id);

	public Item carregarPorNome(String nomItem);

}
