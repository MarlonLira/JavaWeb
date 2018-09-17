package br.com.empresa.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.empresa.interfaces.IExclusaoLogica;
import br.com.empresa.utils.Utils;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends Entidade implements IExclusaoLogica {

	private static final long serialVersionUID = 1L;

	public static final String strTipoUsuario = "tipoUsuario";
	public static final String strNomUsuario = "nomUsuario";
	public static final String strNumMatricula = "numMatricula";
	public static final String strTxtSenha = "txtSenha";
	public static final String strIndRecebeEmail = "indRecebeEmail";
	public static final String strTxtEmail = "txtEmail";
	public static final String strDthExclusao = "dthExclusao";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_usuario")
	private TipoUsuario tipoUsuario;

	@Column(name = "nom_usuario")
	private String nomUsuario;

	@Column(name = "num_matricula")
	private String numMatricula;

	@Column(name = "txt_senha")
	private String txtSenha;

	@Column(name = "ind_recebe_email")
	private String indRecebeEmail;

	@Column(name = "txt_email")
	private String txtEmail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dth_cadastro")
	private Date dthCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dth_exclusao")
	private Date dthExclusao;

	/*
	 * Overriders
	 */

	@Override
	public void setValoresPadrao() throws Exception {
		setDthCadastro(new Date());
		setIndRecebeEmail(Utils.NAO);
	}

	/*
	 * Getters e Setters
	 */

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Date getDthCadastro() {
		return dthCadastro;
	}

	public String getDthCadastroFormatado() {
		return Utils.retornarDataFormatada(getDthCadastro());
	}

	@Override
	public void setDthCadastro(Date dthCadastro) {
		this.dthCadastro = dthCadastro;
	}

	public String getIndRecebeEmail() {
		return indRecebeEmail;
	}

	public String getIndRecebeEmailFormatado() {
		return getIndRecebeEmail().equals(Utils.SIM) ? "Sim" : "Não";
	}

	public void setIndRecebeEmail(String indRecebeEmail) {
		this.indRecebeEmail = indRecebeEmail;
	}

	public String getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(String txtSenha) {
		this.txtSenha = txtSenha;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}

	public Date getDthExclusao() {
		return dthExclusao;
	}

	public void setDthExclusao(Date dthExclusao) {
		this.dthExclusao = dthExclusao;
	}

	/*
	 * Hash e Equals
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(String numMatricula) {
		this.numMatricula = numMatricula;
	}

}
