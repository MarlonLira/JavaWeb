package br.com.empresa.rn;

import java.util.List;

import br.com.empresa.dao.DAOFactory;
import br.com.empresa.dao.EquipamentoDAO;
import br.com.empresa.entidade.Equipamento;

public class EquipamentoRN {

	private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

	/*
	 * Construtor
	 */

	public EquipamentoRN() {
		setEquipamentoDAO((EquipamentoDAO) DAOFactory.novoEquipamento());
	}

	/*
	 * Especificos
	 */

	public void inserir(Equipamento equipamento) throws Exception {
		checaRestricoes(equipamento);

		if (equipamento.getId() == null) {
			getEquipamentoDAO().inserir(equipamento);
		} else {
			getEquipamentoDAO().alterar(equipamento);
		}
	}

	public void excluir(Equipamento equipamento) {
		getEquipamentoDAO().excluir(equipamento);
	}

	public List<Equipamento> listarTodos() {
		return getEquipamentoDAO().listarTodos();
	}

	public Equipamento carregar(Long id) {
		return getEquipamentoDAO().carregar(id);
	}

	private void checaRestricoes(Equipamento equipamento) throws Exception {

	}

	/*
	 * Getters e Setters
	 */

	public EquipamentoDAO getEquipamentoDAO() {
		return equipamentoDAO;
	}

	public void setEquipamentoDAO(EquipamentoDAO equipamentoDAO) {
		this.equipamentoDAO = equipamentoDAO;
	}

}
