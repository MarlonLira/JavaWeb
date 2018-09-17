package br.com.empresa.exceptions;

public class RestricaoPersonalizada extends Exception {

	private static final long serialVersionUID = 1L;

	public RestricaoPersonalizada() {

	}

	public RestricaoPersonalizada(String message) {
		super(message);
	}

}
