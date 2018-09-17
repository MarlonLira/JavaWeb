package br.com.empresa.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_tipo_usuario")
public class TipoUsuario extends Entidade {
	private static final long serialVersionUID = 1L;

	public static final String strNomTipoUsuario = "nomTipoUsuario";
	public static final String strSglTipoUsuario = "sglTipoUsuario";

	public static final String TIPO_ADMIN = "A";
	public static final String TIPO_OPERADOR = "O";
	public static final String TIPO_MASTER = "M";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_tipo_usuario")
	private String nomTipoUsuario;

	@Column(name = "sgl_tipo_usuario")
	private String sglTipoUsuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dth_cadastro")
	private Date dthCadastro;

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

	public String getNomTipoUsuario() {
		return nomTipoUsuario;
	}

	public void setNomTipoUsuario(String nomTipoUsuario) {
		this.nomTipoUsuario = nomTipoUsuario;
	}

	public String getSglTipoUsuario() {
		return sglTipoUsuario;
	}

	public void setSglTipoUsuario(String sglTipoUsuario) {
		this.sglTipoUsuario = sglTipoUsuario;
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
		TipoUsuario other = (TipoUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
