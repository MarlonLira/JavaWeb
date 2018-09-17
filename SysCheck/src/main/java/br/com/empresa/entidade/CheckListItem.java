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
@Table(name = "tb_check_list_item")
public class CheckListItem extends Entidade implements IExclusaoLogica {

	private static final long serialVersionUID = 1L;

	public static final String strCheckList = "checkList";
	public static final String strItem = "item";
	public static final String strDthExclusao = "dthExclusao";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_check_list", nullable = false)
	private CheckList checkList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_item", nullable = false)
	private Item item;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dth_cadastro", nullable = false)
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

	@Override
	public void setDthExclusao(Date dthExclusao) {
		this.dthExclusao = dthExclusao;
	}

	@Override
	public Date getDthExclusao() {
		return dthExclusao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
		CheckListItem other = (CheckListItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
