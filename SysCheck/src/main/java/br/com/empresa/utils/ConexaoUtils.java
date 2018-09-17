package br.com.empresa.utils;

public class ConexaoUtils {
	
	protected static final String IP_LOCAL = "127.0.0.1";
	protected static final String IP_DESENVOLVIMENTO = "0.0.0.0";
	protected static final String IP_HOMOLOGACAO = "0.0.0.0";
	protected static final String IP_PRODUCAO = "0.0.0.0";
	
	protected static final String URL_PADRAO = "jdbc:postgresql://";
	protected static final String PORTA_PADRAO = ":5432";
	protected static final String NOME_BASE = "sysCheck";
	protected static final String USUARIO = "postgres";
	protected static final String SENHA = "5564";
	
	protected static boolean isDesenvolvimento = false;
	protected static boolean isProducao = false;
	protected static boolean isHomologacao = true;
	protected static boolean isLocal = true;

	public static String host() {
		if (isLocal) {
			return IP_LOCAL;
		} else if (isDesenvolvimento) {
			return IP_DESENVOLVIMENTO;
		} else if (isHomologacao) {
			return IP_HOMOLOGACAO;
		} else if (isProducao) {
			return IP_HOMOLOGACAO;
		} else {
			return IP_LOCAL;
		}
	}
	
	public static String url() {
		return URL_PADRAO + host() + PORTA_PADRAO + "/" + NOME_BASE;
	}
}
