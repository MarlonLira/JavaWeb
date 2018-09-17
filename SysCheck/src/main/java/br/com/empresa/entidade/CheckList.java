package br.com.empresa.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.empresa.interfaces.IExclusaoLogica;
import br.com.empresa.utils.Utils;

@Entity
@Table(name = "tb_check_list")
public class CheckList extends Entidade implements IExclusaoLogica {

	private static final long serialVersionUID = 1L;

	public static final String strUsuarioCadastro = "usuarioCadastro";
	public static final String strItemSet = "itemSet";
	public static final String strNomCheckList = "nomCheckList";
	public static final String strDthExclusao = "dthExclusao";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario usuarioCadastro;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "checkList")
	private List<CheckListItem> listCheckListItem;

	@Column(name = "nom_check_list")
	private String nomCheckList;

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
	 * Getters e Setter
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

	public String getNomCheckList() {
		return nomCheckList;
	}

	public void setNomCheckList(String nomCheckList) {
		this.nomCheckList = nomCheckList;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	@Override
	public void setDthExclusao(Date dthExclusao) {
		this.dthExclusao = dthExclusao;
	}

	@Override
	public Date getDthExclusao() {
		return dthExclusao;
	}

	public List<CheckListItem> getListCheckListItem() {
		return listCheckListItem;
	}

	public void setListCheckListItem(List<CheckListItem> listCheckListItem) {
		this.listCheckListItem = listCheckListItem;
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
		CheckList other = (CheckList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
