package br.com.empresa.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.empresa.entidade.Item;
import br.com.empresa.rn.ItemRN;
import br.com.empresa.utils.Utils;

@ManagedBean
@ViewScoped
public class ItemController extends ControllerPadrao<Item> {

	private static final long serialVersionUID = 1L;

	/*
	 * Construtor
	 */

	public ItemController() {
		setEntidade(new Item());
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

	/*
	 * Overriders
	 */

	@Override
	public void novo() throws Exception {
		limpar();
	}

	@Override
	public void editar() throws Exception {

	}

	@Override
	public void excluir() throws Exception {
		ItemRN itemRN = new ItemRN();
		itemRN.excluir(getRegistroSelecionado());
	}

	@Override
	public void salvar() throws Exception {
		ItemRN itemRN = new ItemRN();
		itemRN.inserir(getEntidade());
	}

	@Override
	public void pesquisar() throws Exception {
		ItemRN itemRN = new ItemRN();
		setListRegistros(itemRN.listarTodos());
	}

	@Override
	public void limpar() throws Exception {
		setEntidade(new Item());
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

	@Override
	public void selecionar() throws Exception {

	}

}
