package br.com.empresa.rn;

import java.util.List;

import br.com.empresa.dao.CargoDAO;
import br.com.empresa.dao.DAOFactory;
import br.com.empresa.entidade.Cargo;

public class CargoRN {
	private CargoDAO cargoDAO = new CargoDAO();

	/*
	 * Construtor
	 */

	public CargoRN() {
		setCargoDAO((CargoDAO) DAOFactory.novoCargo());
	}

	public void inserir(Cargo cargo) throws Exception {
		checaRestricoes(cargo);

		if (cargo.getId() == null) {
			getCargoDAO().inserir(cargo);
		} else {
			getCargoDAO().alterar(cargo);
		}
	}

	public void excluir(Cargo cargo) {
		getCargoDAO().excluir(cargo);
	}

	public List<Cargo> listarTodos() {
		return getCargoDAO().listarTodos();
	}

	public Cargo carregar(Long id) {
		return getCargoDAO().carregar(id);
	}

	private void checaRestricoes(Cargo usuario) throws Exception {

	}

	/*
	 * Getters e Setters
	 */

	public CargoDAO getCargoDAO() {
		return cargoDAO;
	}

	public void setCargoDAO(CargoDAO cargoDAO) {
		this.cargoDAO = cargoDAO;
	}
}
