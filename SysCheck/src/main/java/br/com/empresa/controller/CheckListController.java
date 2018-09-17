package br.com.empresa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.empresa.entidade.CheckList;
import br.com.empresa.entidade.CheckListItem;
import br.com.empresa.entidade.Item;
import br.com.empresa.rn.CheckListItemRN;
import br.com.empresa.rn.CheckListRN;
import br.com.empresa.rn.ItemRN;
import br.com.empresa.utils.Utils;

@ManagedBean
@ViewScoped
public class CheckListController extends ControllerPadrao<CheckList> {

	private static final long serialVersionUID = 1L;

	private List<SelectItem> listItemSI;
	private List<CheckListItem> listCheckListItem;
	private List<CheckListItem> listCheckListItemRemovidos;
	private Item item;

	/*
	 * Construtor
	 */

	public CheckListController() {
		setEntidade(new CheckList());
	}

	/*
	 * Especificos
	 */

	@PostConstruct
	private void init() {
		try {
			pesquisar();
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	private void carregaItemGrid() throws Exception {
		CheckListItemRN checkListItemRN = new CheckListItemRN();
		getListCheckListItem().clear();

		List<CheckListItem> listAux = checkListItemRN.carregarPorCheckList(getRegistroSelecionado());

		for (CheckListItem checkListItem : listAux) {
			getListCheckListItem().add(checkListItem);
		}
	}

	public void adicionarItemGrid() {
		try {
			CheckListItem checkListItem = new CheckListItem();

			checkListItem.setValoresPadrao();
			checkListItem.setItem(getItem());

			removeSelecItem(getListItemSI(), getItem());

			getListCheckListItem().add(checkListItem);
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	private void removeSelecItem(List<SelectItem> list, Object obj) {
		SelectItem aux = null;

		for (SelectItem selectItem : list) {
			if (selectItem.getValue().equals(obj)) {
				aux = selectItem;
				break;
			}
		}

		list.remove(aux);
	}

	public void removerItemGrid(CheckListItem checkListItem) {
		try {
			if (checkListItem.getId() != null) {
				getListCheckListItemRemovidos().add(checkListItem);
			}

			if (getListCheckListItem().contains(checkListItem)) {
				getListCheckListItem().remove(checkListItem);

				getListItemSI().add(new SelectItem(checkListItem.getItem(), checkListItem.getItem().getId() + " | " + checkListItem.getItem().getNomItem()));
			}
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	private void carregaItens() throws Exception {
		ItemRN itemRN = new ItemRN();
		List<Item> listAux = itemRN.listarTodos();
		getListItemSI().clear();

		if (!getListCheckListItem().isEmpty()) {
			for (CheckListItem checkListItem : getListCheckListItem()) {
				listAux.remove(checkListItem.getItem());
			}
		}

		for (Item item : listAux) {
			getListItemSI().add(new SelectItem(item, item.getId() + " | " + item.getNomItem()));
		}
	}

	/*
	 * Overriders
	 */

	@Override
	public void selecionar() throws Exception {
		carregaItemGrid();
	}

	@Override
	public void novo() throws Exception {
		limpar();
		carregaItens();
	}

	@Override
	public void editar() throws Exception {
		carregaItens();
	}

	@Override
	public void excluir() throws Exception {
		CheckListRN checkListRN = new CheckListRN();
		checkListRN.excluir(getRegistroSelecionado());
	}

	@Override
	public void salvar() throws Exception {
		CheckListRN checkListRN = new CheckListRN();
		checkListRN.inserir(getEntidade());

		CheckListItemRN checkListItemRN = new CheckListItemRN();

		if (!getListCheckListItemRemovidos().isEmpty()) {
			for (CheckListItem checkListItem : getListCheckListItemRemovidos()) {
				checkListItemRN.excluir(checkListItem);
			}
		}

		for (CheckListItem checkListItem : getListCheckListItem()) {
			checkListItem.setCheckList(getEntidade());
			checkListItemRN.inserir(checkListItem);
		}
	}

	@Override
	public void pesquisar() throws Exception {
		CheckListRN checkListRN = new CheckListRN();
		setListRegistros(checkListRN.listarTodos());
	}

	@Override
	public void limpar() throws Exception {
		setEntidade(new CheckList());
		getEntidade().setValoresPadrao();
	}

	@Override
	public void checaRestricoesNovo() throws Exception {

	}

	@Override
	public void checaRestricoesEdicao() throws Exception {

	}

	@Override
	public void checaRestricoesExclusao() throws Exception {

	}

	/*
	 * Getters e Setters
	 */

	public List<SelectItem> getListItemSI() {
		if (this.listItemSI == null) {
			this.listItemSI = new ArrayList<SelectItem>();
		}

		return listItemSI;
	}

	public void setListItemSI(List<SelectItem> listItemSI) {
		this.listItemSI = listItemSI;
	}

	public List<CheckListItem> getListCheckListItem() {
		if (this.listCheckListItem == null) {
			this.listCheckListItem = new ArrayList<CheckListItem>();
		}

		return listCheckListItem;
	}

	public void setListCheckListItem(List<CheckListItem> listCheckListItem) {
		this.listCheckListItem = listCheckListItem;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<CheckListItem> getListCheckListItemRemovidos() {
		if (this.listCheckListItemRemovidos == null) {
			this.listCheckListItemRemovidos = new ArrayList<CheckListItem>();
		}

		return listCheckListItemRemovidos;
	}

	public void setListCheckListItemRemovidos(List<CheckListItem> listCheckListItemRemovidos) {
		this.listCheckListItemRemovidos = listCheckListItemRemovidos;
	}

}
