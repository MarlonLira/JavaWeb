package br.com.empresa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.empresa.entidade.Entidade;
import br.com.empresa.entidade.Usuario;
import br.com.empresa.exceptions.RestricaoPersonalizada;
import br.com.empresa.utils.Utils;

public abstract class ControllerPadrao<T extends Entidade> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T entidade;

	private T registroSelecionado;

	@SuppressWarnings("unused")
	private Usuario usuarioLogado;

	private List<T> listRegistros;

	public abstract void novo() throws Exception;

	public abstract void editar() throws Exception;

	public abstract void excluir() throws Exception;

	public abstract void salvar() throws Exception;

	public abstract void pesquisar() throws Exception;

	public abstract void limpar() throws Exception;

	public abstract void selecionar() throws Exception;

	public abstract void checaRestricoesNovo() throws Exception;

	public abstract void checaRestricoesEdicao() throws Exception;

	public abstract void checaRestricoesExclusao() throws Exception;

	/*
	 * Hooks
	 */

	public void novo(ActionEvent ae) {
		try {
			novo();
		} catch (RestricaoPersonalizada e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent ae) {
		try {
			editar();
		} catch (RestricaoPersonalizada e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void salvar(ActionEvent ae) {
		try {
			checaRestricoesNovo();
			salvar();
			Utils.enviarMensagem(Utils.MENSAGEM_CADASTRO_SUCESSO);
			limpar();
			pesquisar();
			RequestContext.getCurrentInstance().execute("PF('dlgPrincipalWvar').hide()");
		} catch (RestricaoPersonalizada e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent ae) {
		try {
			checaRestricoesExclusao();
			excluir();
			pesquisar();
			Utils.enviarMensagem(Utils.MENSAGEM_EXCLUIDO_SUCESSO);
			limpar();
		} catch (RestricaoPersonalizada e) {
			Utils.enviarMensagem(e.getMessage());
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	/*
	 * Utilitarios
	 */

	@SuppressWarnings("unchecked")
	public void onRowSelect(SelectEvent event) {
		try {
			setRegistroSelecionado((T) event.getObject());
			selecionar();
		} catch (Exception e) {
			Utils.enviarMensagem(Utils.MENSAGEM_ERRO_PADRAO);
			e.printStackTrace();
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		setRegistroSelecionado(null);
	}

	/*
	 * Getters e Setters
	 */

	public T getEntidade() {
		return entidade;
	}

	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

	public List<T> getListRegistros() {
		if (this.listRegistros == null) {
			this.listRegistros = new ArrayList<T>();
		}

		return listRegistros;
	}

	public void setListRegistros(List<T> listRegistros) {
		this.listRegistros = listRegistros;
	}

	public T getRegistroSelecionado() {
		return registroSelecionado;
	}

	public void setRegistroSelecionado(T registroSelecionado) {
		this.registroSelecionado = registroSelecionado;
	}

	public Usuario getUsuarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		return (Usuario) session.getAttribute("usuario");
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
