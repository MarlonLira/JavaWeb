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

@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends Entidade implements IExclusaoLogica {

	private static final long serialVersionUID = 1L;

	public static final String strNomFuncionario = "nomFuncionario";
	public static final String strCargo = "cargo";
	public static final String strUsuario = "usuario";
	public static final String strDthExclusao = "dthExclusao";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_funcionario")
	private String nomFuncionario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

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

	@Override
	public void setDthCadastro(Date dthCadastro) {
		this.dthCadastro = dthCadastro;
	}

	public String getNomFuncionario() {
		return nomFuncionario;
	}

	public void setNomFuncionario(String nomFuncionario) {
		this.nomFuncionario = nomFuncionario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
