package br.com.empresa.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.empresa.entidade.TipoUsuario;
import br.com.empresa.entidade.Usuario;
import br.com.empresa.rn.TipoUsuarioRN;
import br.com.empresa.rn.UsuarioRN;
import br.com.empresa.utils.Utils;

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	/*
	 * Construtor
	 */

	public LoginController() throws Exception {
		UsuarioRN usuarioRN = new UsuarioRN();
		Usuario usuario = usuarioRN.findByLogin(Utils.USUARIO_MASTER, Utils.USUARIO_MASTER);

		TipoUsuarioRN tipoUsuarioRN = new TipoUsuarioRN();
		TipoUsuario tipoUsuario = tipoUsuarioRN.carregarPorTipo(TipoUsuario.TIPO_MASTER);

		if (tipoUsuario == null) {
			tipoUsuario = new TipoUsuario();

			tipoUsuario.setValoresPadrao();
			tipoUsuario.setNomTipoUsuario("Master");
			tipoUsuario.setSglTipoUsuario(TipoUsuario.TIPO_MASTER);

			tipoUsuarioRN.inserir(tipoUsuario);
		}

		if (usuario == null) {
			usuario = new Usuario();

			usuario.setValoresPadrao();
			usuario.setNomUsuario(Utils.USUARIO_MASTER);
			usuario.setTxtSenha(Utils.USUARIO_MASTER);
			usuario.setTipoUsuario(tipoUsuario);
			usuario.setNumMatricula("000000");

			usuarioRN.inserir(usuario);
		}
	}

	/*
	 * Especificos
	 */

	public String doLogin() throws Exception {
		try {
			UsuarioRN usuarioRN = new UsuarioRN();

			setUsuario(usuarioRN.findByLogin(getUsuario().getNomUsuario(), getUsuario().getTxtSenha()));

			if (Utils.isNotNull(getUsuario())) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

				session.setAttribute("usuario", usuario);

				return "/restricted/forms/frm_index?faces-redirect=true";
			} else {
				throw new Exception("Favor verificar o nome de usuário e a senha.");
			}
		} catch (Exception e) {
			setUsuario(new Usuario());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "/frm_login?faces-redirect=true";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/frm_login?faces-redirect=true";
	}

	public boolean isUsuarioAdmin() {
		return getUsuarioLogado().getTipoUsuario().getSglTipoUsuario().equals(TipoUsuario.TIPO_ADMIN) || getUsuarioLogado().getTipoUsuario().getSglTipoUsuario().equals(TipoUsuario.TIPO_MASTER);
	}

	/*
	 * Getters e Setters
	 */

	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario getUsuarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return (Usuario) session.getAttribute("usuario");
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
