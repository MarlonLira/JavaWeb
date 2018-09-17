package br.com.empresa.entidade;

import java.io.Serializable;
import java.util.Date;

public abstract class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String strId = "id";
	public static final String strDthCadastro = "dthCadastro";

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract Date getDthCadastro();

	public abstract void setDthCadastro(Date dthCadastro);

	public abstract void setValoresPadrao() throws Exception;

}
