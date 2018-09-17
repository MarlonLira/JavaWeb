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
@Table(name = "tb_equipamento")
public class Equipamento extends Entidade implements IExclusaoLogica {

	private static final long serialVersionUID = 1L;

	public static final String strCheckList = "checkList";
	public static final String strNomEquipamento = "nomEquipamento";
	public static final String strCodIdentificador = "codIndenteficador";
	public static final String strTxtObservacao = "txtObservacao";
	public static final String strDthExclusao = "dthExclusao";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_check_list")
	private CheckList checkList;

	@Column(name = "nom_equipamento")
	private String nomEquipamento;

	@Column(name = "cod_identificador")
	private String codIdentificador;

	@Column(name = "txt_observacao")
	private String txtObservacao;

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
	public void setDthExclusao(Date dthExclusao) {
		this.dthExclusao = dthExclusao;
	}

	@Override
	public Date getDthExclusao() {
		return dthExclusao;
	}

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

	public String getNomEquipamento() {
		return nomEquipamento;
	}

	public void setNomEquipamento(String nomEquipamento) {
		this.nomEquipamento = nomEquipamento;
	}

	public String getCodIdentificador() {
		return codIdentificador;
	}

	public void setCodIdentificador(String codIdentificador) {
		this.codIdentificador = codIdentificador;
	}

	public String getTxtObservacao() {
		return txtObservacao;
	}

	public void setTxtObservacao(String txtObservacao) {
		this.txtObservacao = txtObservacao;
	}

	public CheckList getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckList checkList) {
		this.checkList = checkList;
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
		Equipamento other = (Equipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
