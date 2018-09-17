package br.com.empresa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.empresa.entidade.CheckList;
import br.com.empresa.entidade.Equipamento;
import br.com.empresa.rn.CheckListRN;
import br.com.empresa.rn.EquipamentoRN;
import br.com.empresa.utils.Utils;

@ManagedBean
@ViewScoped
public class EquipamentoController extends ControllerPadrao<Equipamento> {

	private static final long serialVersionUID = 1L;

	private List<SelectItem> listCheckListSI;

	/*
	 * Construtor
	 */

	public EquipamentoController() {
		setEntidade(new Equipamento());
	}

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
	 * Especificos
	 */

	private void carregaCheckList() throws Exception {
		CheckListRN checkListRN = new CheckListRN();
		List<CheckList> listAux = checkListRN.listarTodos();

		getListCheckListSI().clear();

		for (CheckList checkList : listAux) {
			getListCheckListSI().add(new SelectItem(checkList, checkList.getId() + " | " + checkList.getNomCheckList()));
		}
	}

	/*
	 * Overriders
	 */

	@Override
	public void novo() throws Exception {
		limpar();
		carregaCheckList();
	}

	@Override
	public void editar() throws Exception {
		carregaCheckList();
	}

	@Override
	public void excluir() throws Exception {
		EquipamentoRN equipamentoRN = new EquipamentoRN();
		equipamentoRN.excluir(getRegistroSelecionado());
	}

	@Override
	public void salvar() throws Exception {
		EquipamentoRN equipamentoRN = new EquipamentoRN();
		equipamentoRN.inserir(getEntidade());
	}

	@Override
	public void pesquisar() throws Exception {
		EquipamentoRN equipamentoRN = new EquipamentoRN();
		setListRegistros(equipamentoRN.listarTodos());
	}

	@Override
	public void limpar() throws Exception {
		setEntidade(new Equipamento());
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

	/*
	 * Getters e Setters
	 */

	public List<SelectItem> getListCheckListSI() {
		if (this.listCheckListSI == null) {
			this.listCheckListSI = new ArrayList<SelectItem>();
		}

		return listCheckListSI;
	}

	public void setListCheckListSI(List<SelectItem> listCheckListSI) {
		this.listCheckListSI = listCheckListSI;
	}

}
