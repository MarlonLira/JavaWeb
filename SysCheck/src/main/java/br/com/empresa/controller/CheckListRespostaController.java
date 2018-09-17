package br.com.empresa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.google.gson.Gson;

import br.com.empresa.entidade.CheckList;
import br.com.empresa.entidade.CheckListItem;
import br.com.empresa.entidade.CheckListResposta;
import br.com.empresa.entidade.Equipamento;
import br.com.empresa.entidade.Usuario;
import br.com.empresa.exceptions.RestricaoPersonalizada;
import br.com.empresa.rn.CheckListItemRN;
import br.com.empresa.rn.CheckListRespostaRN;
import br.com.empresa.rn.EquipamentoRN;
import br.com.empresa.rn.UsuarioRN;
import br.com.empresa.utils.Utils;
import br.com.empresa.utils.wrappers.CheckListRespostaWrapper;
import br.com.empresa.utils.wrappers.ItemRespostaWrapper;

@ManagedBean
@ViewScoped
public class CheckListRespostaController extends ControllerPadrao<CheckListResposta> {

	private static final long serialVersionUID = 1L;

	private List<SelectItem> listEquipamentoSI;

	private CheckListRespostaWrapper checkListRespostaWrapper;
	private List<CheckListRespostaWrapper> listCheckListRespostaWrapper;
	private Equipamento equipamento;
	private CheckList checkList;

	/*
	 * Construtor
	 */

	public CheckListRespostaController() {
		setEntidade(new CheckListResposta());
	}

	/*
	 * Especificos
	 */

	@PostConstruct
	private void init() {
		try {
			carregaEquipamentos();
			carregaCheckListRespondido();
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	private void carregaCheckListRespondido() throws Exception {
		CheckListRespostaRN checkListRespostaRN = new CheckListRespostaRN();
		List<CheckListResposta> listAux = checkListRespostaRN.listarTodos();

		for (CheckListResposta checkListResposta : listAux) {
			Gson gson = new Gson();
			CheckListRespostaWrapper checkListRespostaWrapper = gson.fromJson(checkListResposta.getTxtJsonResposta(), CheckListRespostaWrapper.class);

			getListCheckListRespostaWrapper().add(checkListRespostaWrapper);
		}
	}

	private void carregaEquipamentos() throws Exception {
		getListEquipamentoSI().clear();

		EquipamentoRN equipamentoRN = new EquipamentoRN();
		List<Equipamento> listAux = equipamentoRN.listarTodos();

		for (Equipamento equipamento : listAux) {
			getListEquipamentoSI().add(new SelectItem(equipamento, equipamento.getId() + " | " + equipamento.getNomEquipamento()));
		}
	}

	public void selecionaEquipamento() {
		try {
			CheckListItemRN checkListItemRN = new CheckListItemRN();
			List<CheckListItem> listCheckListItem = checkListItemRN.carregarPorCheckList(getEquipamento().getCheckList());

			if (listCheckListItem == null || listCheckListItem.isEmpty()) {
				throw new RestricaoPersonalizada("Não existe ficha vinculáda a esse equipamento. Favor contatar o setor responsável pelo cadastro.");
			}

			montarGridResposta(listCheckListItem);
		} catch (RestricaoPersonalizada e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	private void montarGridResposta(List<CheckListItem> listCheckListItem) throws Exception {
		setCheckList(listCheckListItem.get(0).getCheckList());

		setCheckListRespostaWrapper(new CheckListRespostaWrapper());

		getCheckListRespostaWrapper().setCheckList(getCheckList().getNomCheckList());
		getCheckListRespostaWrapper().setData(Utils.retornarDataHoraFormatada(new Date()));
		getCheckListRespostaWrapper().setEquipamento(getEquipamento().getNomEquipamento());
		getCheckListRespostaWrapper().setUsuario(getUsuarioLogado().getNomUsuario());

		getCheckListRespostaWrapper().setItens(new ArrayList<ItemRespostaWrapper>());

		for (CheckListItem checkListItem : listCheckListItem) {
			ItemRespostaWrapper itemRespostaWrapper = new ItemRespostaWrapper();

			itemRespostaWrapper.setItem(checkListItem.getItem().getNomItem());

			getCheckListRespostaWrapper().getItens().add(itemRespostaWrapper);
		}
	}

	/*
	 * Overriders
	 */

	@Override
	public void novo() throws Exception {

	}

	@Override
	public void editar() throws Exception {

	}

	@Override
	public void excluir() throws Exception {

	}

	private String criarJson() throws Exception {
		Gson gson = new Gson();

		return gson.toJson(getCheckListRespostaWrapper());
	}

	@Override
	public void salvar() throws Exception {
		StringBuilder sbEmail = new StringBuilder();
		boolean problema = false;

		if (getCheckListRespostaWrapper().getItens() != null) {
			for (ItemRespostaWrapper itemRespostaWrapper : getCheckListRespostaWrapper().getItens()) {
				if (itemRespostaWrapper.getStatus().equals("P")) {
					problema = true;
					break;
				}
			}

			if (problema) {
				sbEmail.append("Equipamento com problema: " + getEquipamento().getNomEquipamento());
				sbEmail.append(System.getProperty("line.separator"));
				sbEmail.append("Operador: " + getCheckListRespostaWrapper().getUsuario());
				sbEmail.append(System.getProperty("line.separator"));
				sbEmail.append("Operador: " + getCheckListRespostaWrapper().getData());
				sbEmail.append(System.getProperty("line.separator"));

				sbEmail.append("Item").append(";");
				sbEmail.append("Status").append(";");
				sbEmail.append("Observação").append(";");
				sbEmail.append(System.getProperty("line.separator"));

				for (ItemRespostaWrapper itemRespostaWrapper : getCheckListRespostaWrapper().getItens()) {
					sbEmail.append(itemRespostaWrapper.getItem()).append(";");
					sbEmail.append(itemRespostaWrapper.getStatus().equals("O") ? "Ok" : "Problema").append(";");
					sbEmail.append(itemRespostaWrapper.getObservacao()).append(";");
					sbEmail.append(System.getProperty("line.separator"));
				}
			}
		}

		if (sbEmail.length() > 0) {
			UsuarioRN usuarioRN = new UsuarioRN();
			List<Usuario> listUsuarios = usuarioRN.listarTodos();
			Map<String, byte[]> anexo = new HashMap<String, byte[]>();

			anexo.put("check_list_problema_" + Utils.retornaDataHoraSemSeparador(new Date()) + ".csv", sbEmail.toString().getBytes());

			for (Usuario usuario : listUsuarios) {
				if (usuario.getIndRecebeEmail().equals(Utils.SIM)) {
					Utils.enviaEmail("Maquina com problema - " + getEquipamento().getNomEquipamento(), "Verificado que alguns itens do equipamento " + getEquipamento().getNomEquipamento() + " estão com problemas. Segue anexo a ficha reportada pelo operador.", usuario.getTxtEmail(), anexo);
				}
			}
		}

		getEntidade().setValoresPadrao();
		getEntidade().setUsuario(getUsuarioLogado());
		getEntidade().setCheckList(getCheckList());
		getEntidade().setEquipamento(getEquipamento());
		getEntidade().setTxtJsonResposta(criarJson());

		CheckListRespostaRN checkListRespostaRN = new CheckListRespostaRN();
		checkListRespostaRN.inserir(getEntidade());
	}

	@Override
	public void pesquisar() throws Exception {

	}

	@Override
	public void limpar() throws Exception {
		setCheckList(null);
		setCheckListRespostaWrapper(null);
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

	public List<SelectItem> getListEquipamentoSI() {
		if (this.listEquipamentoSI == null) {
			this.listEquipamentoSI = new ArrayList<SelectItem>();
		}

		return listEquipamentoSI;
	}

	public void setListEquipamentoSI(List<SelectItem> listEquipamentoSI) {
		this.listEquipamentoSI = listEquipamentoSI;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public CheckListRespostaWrapper getCheckListRespostaWrapper() {
		return checkListRespostaWrapper;
	}

	public void setCheckListRespostaWrapper(CheckListRespostaWrapper checkListRespostaWrapper) {
		this.checkListRespostaWrapper = checkListRespostaWrapper;
	}

	public CheckList getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckList checkList) {
		this.checkList = checkList;
	}

	public List<CheckListRespostaWrapper> getListCheckListRespostaWrapper() {
		if (this.listCheckListRespostaWrapper == null) {
			this.listCheckListRespostaWrapper = new ArrayList<CheckListRespostaWrapper>();
		}

		return listCheckListRespostaWrapper;
	}

	public void setListCheckListRespostaWrapper(List<CheckListRespostaWrapper> listCheckListRespostaWrapper) {
		this.listCheckListRespostaWrapper = listCheckListRespostaWrapper;
	}

}
