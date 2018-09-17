package br.com.empresa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.empresa.entidade.CheckListResposta;
import br.com.empresa.interfaces.ICheckListResposta;

public class CheckListRespostaDAO implements ICheckListResposta {

	private Session session;

	@Override
	public void inserir(CheckListResposta checkListResposta) {
		getSession().save(checkListResposta);
	}

	@Override
	public void alterar(CheckListResposta checkListResposta) {
		getSession().update(checkListResposta);
	}

	@Override
	public void excluir(CheckListResposta checkListResposta) {
		getSession().delete(checkListResposta);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckListResposta> listarTodos() {
		Criteria ct = getSession().createCriteria(CheckListResposta.class);

		return ct.list();
	}

	@Override
	public CheckListResposta carregar(Long id) {
		return null;
	}

	/*
	 * Getters e Setters
	 */

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
