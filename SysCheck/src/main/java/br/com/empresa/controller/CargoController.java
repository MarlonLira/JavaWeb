package br.com.empresa.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.empresa.entidade.Cargo;
import br.com.empresa.rn.CargoRN;
import br.com.empresa.utils.Utils;

@ManagedBean
@ViewScoped
public class CargoController extends ControllerPadrao<Cargo> {

	private static final long serialVersionUID = 1L;

	/*
	 * Construtor
	 */

	public CargoController() {
		setEntidade(new Cargo());
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

	@Override
	public void novo() throws Exception {
		limpar();
	}

	@Override
	public void editar() throws Exception {

	}

	@Override
	public void excluir() throws Exception {
		CargoRN cargoRN = new CargoRN();
		cargoRN.excluir(getRegistroSelecionado());
	}

	@Override
	public void salvar() throws Exception {
		CargoRN cargoRN = new CargoRN();
		cargoRN.inserir(getEntidade());
	}

	@Override
	public void pesquisar() throws Exception {
		CargoRN cargoRN = new CargoRN();
		setListRegistros(cargoRN.listarTodos());
	}

	@Override
	public void limpar() throws Exception {
		setEntidade(new Cargo());
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
