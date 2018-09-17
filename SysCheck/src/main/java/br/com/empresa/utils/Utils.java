package br.com.empresa.utils;

import java.security.MessageDigest;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import br.com.empresa.entidade.Entidade;

public class Utils {

	public static final String SIM = "S";
	public static final String NAO = "N";

	public static final String USUARIO_MASTER = "master";

	protected static final String USER_MAIL = "syschecklist@gmail.com";
	protected static final String PASS_MAIL = "syscheck5564";

	public static final String MENSAGEM_CADASTRO_SUCESSO = "Registro salvo com sucesso.";
	public static final String MENSAGEM_ALTERADO_SUCESSO = "Registro alterado com sucesso.";
	public static final String MENSAGEM_EXCLUIDO_SUCESSO = "Registro excluído com sucesso.";
	public static final String MENSAGEM_ERRO_CADASTRO = "Erro ao cadastrar dados. Tente novamente. Se persistir entre em contato com o suporte";
	public static final String MENSAGEM_ERRO_EXCLUIR = "Erro ao excluir dados. Tente novamente. Se persistir entre em contato com o suporte";
	public static final String MENSAGEM_ERRO_LISTAR = "Erro ao listar dados. Tente novamente. Se persistir entre em contato com o suporte";
	public static final String MENSAGEM_ERRO_PADRAO = "Ocorreu um erro inesperado. Tente novamente. Se persistir entre em contato com o suporte";

	public static final String STATUS_ATIVO = "A";
	public static final String STATUS_INATIVO = "I";

	/*
	 * Utilitarios
	 */

	public static void enviaEmail(String assunto, String mensagem, String email, Map<String, byte[]> anexos) {
		try {
			Properties mailProps = new Properties();

			mailProps.put("mail.transport.protocol", "smtp");
			mailProps.put("mail.smtp.starttls.enable", "true");
			mailProps.put("mail.smtp.host", "smtp.gmail.com");
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("mail.smtp.user", USER_MAIL);
			mailProps.put("mail.debug", "true");
			mailProps.put("mail.smtp.port", "465");
			mailProps.put("mail.smtp.socketFactory.port", "465");
			mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			mailProps.put("mail.smtp.socketFactory.fallback", "false");

			Session mailSession = Session.getInstance(mailProps, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USER_MAIL, PASS_MAIL);
				}
			});

			mailSession.setDebug(false);

			Message mailMessage = new MimeMessage(mailSession);

			mailMessage.setFrom(new InternetAddress(USER_MAIL));
			mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			MimeBodyPart mbpMensagem = new MimeBodyPart();
			mbpMensagem.setText(mensagem);

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbpMensagem);

			if (anexos != null) {
				for (Entry<String, byte[]> entry : anexos.entrySet()) {
					MimeBodyPart mbpAnexo = new MimeBodyPart();

					mbpAnexo.setDataHandler(new DataHandler(new ByteArrayDataSource(entry.getValue(), "application/text")));
					mbpAnexo.setFileName(entry.getKey());

					mp.addBodyPart(mbpAnexo);
				}
			}

			mailMessage.setSubject(assunto);
			mailMessage.setContent(mp);

			Transport.send(mailMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String retornarDataFormatada(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(data);
	}

	public static String retornarDataHoraFormatada(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return simpleDateFormat.format(data);
	}

	public static String retornaDataSemSeparador(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
		return simpleDateFormat.format(data);
	}

	public static String retornaDataHoraSemSeparador(Date data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		return simpleDateFormat.format(data);
	}

	public static String converterLongToReais(Long valor) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(valor);
	}

	public static String converterDoubleToReais(Double valor) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(valor);
	}

	public static boolean isNotNull(Entidade entidade) {
		return entidade != null && entidade.getId() != null;
	}

	public static String criptografaSenha(String senha) {
		String senhaCriptografada = "";

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte digest[] = messageDigest.digest(senha.getBytes("UTF-8"));
			StringBuilder sbSenha = new StringBuilder();

			for (byte b : digest) {
				sbSenha.append(String.format("%02X", 0xFF & b));
			}

			senhaCriptografada = sbSenha.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return senhaCriptografada;
	}

	public static void enviarMensagem(String mensagem) {
		enviarMensagem(mensagem, "", FacesMessage.SEVERITY_INFO);
	}

	public static void enviarMensagem(String mensagem, String detalhe, Severity nivel) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(nivel, detalhe, mensagem));
	}
}
