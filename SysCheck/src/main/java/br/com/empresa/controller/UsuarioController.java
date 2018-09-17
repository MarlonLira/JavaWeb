package br.com.empresa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.empresa.entidade.TipoUsuario;
import br.com.empresa.entidade.Usuario;
import br.com.empresa.rn.TipoUsuarioRN;
import br.com.empresa.rn.UsuarioRN;
import br.com.empresa.utils.Utils;

@ManagedBean
@ViewScoped
public class UsuarioController extends ControllerPadrao<Usuario> {

	private static final long serialVersionUID = 1L;

	private List<SelectItem> listTipoUsuarioSI;

	//teste de commit
	
	
	/*
	 * Construtor
	 */

	public UsuarioController() {
		setEntidade(new Usuario());
		verificaTipoUsuario();
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
	 * Overriders
	 */

	@Override
	public void novo() throws Exception {
		limpar();
		carregaTipoUsuario();
	}

	@Override
	public void editar() throws Exception {
		carregaTipoUsuario();
	}

	@Override
	public void excluir() throws Exception {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(getRegistroSelecionado());
	}

	@Override
	public void salvar() throws Exception {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.inserir(getEntidade());
	}

	@Override
	public void pesquisar() throws Exception {
		UsuarioRN usuarioRN = new UsuarioRN();
		setListRegistros(usuarioRN.listarTodos());
	}

	@Override
	public void limpar() throws Exception {
		setEntidade(new Usuario());
		getEntidade().setValoresPadrao();
		setListTipoUsuarioSI(null);
		carregaTipoUsuario();
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
	 * Especificos
	 */

	private void verificaTipoUsuario() {
		try {
			TipoUsuarioRN tipoUsuarioRN = new TipoUsuarioRN();
			List<TipoUsuario> listTipoUsuario = tipoUsuarioRN.listarTodos();

			if (listTipoUsuario == null || listTipoUsuario.isEmpty()) {
				TipoUsuario tipoUsuario = new TipoUsuario();

				tipoUsuario.setValoresPadrao();
				tipoUsuario.setNomTipoUsuario("Administrador");
				tipoUsuario.setSglTipoUsuario(TipoUsuario.TIPO_ADMIN);

				tipoUsuarioRN.inserir(tipoUsuario);

				tipoUsuario = new TipoUsuario();
				tipoUsuario.setValoresPadrao();
				tipoUsuario.setNomTipoUsuario("Operador");
				tipoUsuario.setSglTipoUsuario(TipoUsuario.TIPO_OPERADOR);

				tipoUsuarioRN.inserir(tipoUsuario);
			}
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	private void carregaTipoUsuario() throws Exception {
		TipoUsuarioRN tipoUsuarioRN = new TipoUsuarioRN();

		List<TipoUsuario> listAux = tipoUsuarioRN.listarTodos();

		if (!getListTipoUsuarioSI().isEmpty()) {
			getListTipoUsuarioSI().clear();
		}

		for (TipoUsuario tipoUsuario : listAux) {
			getListTipoUsuarioSI().add(new SelectItem(tipoUsuario, tipoUsuario.getId() + " | " + tipoUsuario.getNomTipoUsuario()));
		}
	}

	/*
	 * Getters e Setters
	 */

	public List<SelectItem> getListTipoUsuarioSI() {
		if (this.listTipoUsuarioSI == null) {
			this.listTipoUsuarioSI = new ArrayList<SelectItem>();
		}

		return listTipoUsuarioSI;
	}

	public void setListTipoUsuarioSI(List<SelectItem> listTipoUsuarioSI) {
		this.listTipoUsuarioSI = listTipoUsuarioSI;
	}

}
