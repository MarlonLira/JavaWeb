package br.com.empresa.dao;

import br.com.empresa.interfaces.ICargo;
import br.com.empresa.interfaces.ICheckList;
import br.com.empresa.interfaces.ICheckListItem;
import br.com.empresa.interfaces.ICheckListResposta;
import br.com.empresa.interfaces.IEquipamento;
import br.com.empresa.interfaces.IItem;
import br.com.empresa.interfaces.ITipoUsuario;
import br.com.empresa.interfaces.IUsuario;
import br.com.empresa.utils.HibernateUtil;

public class DAOFactory {

	public static IUsuario novoUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}

	public static ICargo novoCargo() {
		CargoDAO cargoDAO = new CargoDAO();
		cargoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return cargoDAO;
	}

	public static IEquipamento novoEquipamento() {
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		equipamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return equipamentoDAO;
	}

	public static ITipoUsuario novoTipoUsuario() {
		TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
		tipoUsuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return tipoUsuarioDAO;
	}

	public static IItem novoItem() {
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return itemDAO;
	}

	public static ICheckList novoCheckList() {
		CheckListDAO checkListDAO = new CheckListDAO();
		checkListDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return checkListDAO;
	}

	public static ICheckListItem novoCheckListItem() {
		CheckListItemDAO checkListItemDAO = new CheckListItemDAO();
		checkListItemDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return checkListItemDAO;
	}

	public static ICheckListResposta novoCheckListResposta() {
		CheckListRespostaDAO checkListRespostaDAO = new CheckListRespostaDAO();
		checkListRespostaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return checkListRespostaDAO;
	}

}
