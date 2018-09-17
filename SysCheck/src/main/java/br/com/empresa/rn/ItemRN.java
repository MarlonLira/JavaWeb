package br.com.empresa.rn;

import java.util.List;

import br.com.empresa.dao.DAOFactory;
import br.com.empresa.dao.ItemDAO;
import br.com.empresa.entidade.Item;
import br.com.empresa.exceptions.RestricaoPersonalizada;

public class ItemRN {

	private ItemDAO itemDAO = new ItemDAO();

	/*
	 * Construtor
	 */

	public ItemRN() {
		setItemDAO((ItemDAO) DAOFactory.novoItem());
	}

	/*
	 * Especificos
	 */

	public void inserir(Item item) throws Exception {
		checaRestricoes(item);

		if (item.getId() == null) {
			getItemDAO().inserir(item);
		} else {
			getItemDAO().alterar(item);
		}
	}

	public void excluir(Item item) {
		getItemDAO().excluir(item);
	}

	public List<Item> listarTodos() {
		return getItemDAO().listarTodos();
	}

	public Item carregar(Long id) {
		return getItemDAO().carregar(id);
	}

	public boolean verificaNomeItem(String nomItem) {
		return getItemDAO().carregarPorNome(nomItem.trim()) != null;
	}

	private void checaRestricoes(Item item) throws Exception {
		if (item.getNomItem() == null || item.getNomItem().trim().isEmpty()) {
			throw new RestricaoPersonalizada("Favor informar o nome do item.");
		}

		if (verificaNomeItem(item.getNomItem())) {
			throw new RestricaoPersonalizada("Já existe um item cadastrado com esse nome, favor utilizar um nome diferente.");
		}
	}

	/*
	 * Getters e Setters
	 */

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

}
