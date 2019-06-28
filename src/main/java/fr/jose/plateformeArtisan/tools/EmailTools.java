package fr.jose.plateformeArtisan.tools;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailTools {

	private static final String SMTP_SERVER = "smtp.dsl.ovh.net";
	private static final String EMAIL_SENDER = "noreply@gmail.com";
//	private static final String EMAIL_RECEIVER = "jose.blairon@sfr.fr";
	private static final String EMAIL_TO = "blairon.jose@gmail.com";

	public static void sendEmail(String to, String subject, String msg) throws Exception {
		Email email = new SimpleEmail();
		email.setHostName(SMTP_SERVER);
//		email.setHostName("smtp.googlemail.com");

//		email.setSmtpPort(25);
//		email.setAuthenticator(new DefaultAuthenticator("username", "password"));
//		email.setSSLOnConnect(true);
		email.setFrom(EMAIL_SENDER);
		email.setSubject(subject);
		email.setMsg(msg);
		email.addTo(to);
		email.send();
	}

	public static String sendEmailToAdmin(String from, String subject, String msg) {
//		String userName = "culture.eventsdawan@gmail.com";
//		String password = "dawan2018";
		
		System.out.println("From = " + from);
		
		String userName = "culture.eventsdawan@gmail.com";
		String password = "dawan2018";
		
		String messageErreur = null;
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setAuthentication(userName, password);
		email.setSmtpPort(465);
		email.setSSL(true);
		
		try {
			email.setFrom(from);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		email.setSubject(subject);
		try {
			email.setMsg(msg);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.addTo(EMAIL_TO);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.send();
		} catch (EmailException e) {
			messageErreur = "Une erreur s'est produite, veuillez r�essayer";
			e.printStackTrace();
		}
		
		return messageErreur;
	}
	
	
	public static String sendEmailToArtisan(String from, String subject, String msg, String emailTo) {
//		String userName = "culture.eventsdawan@gmail.com";
//		String password = "dawan2018";
		
		String userName = "culture.eventsdawan@gmail.com";
		String password = "dawan2018";
		
		String messageErreur = null;
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setAuthentication(userName, password);
		email.setSmtpPort(465);
		email.setSSL(true);
		
		try {
			email.setFrom(from);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		email.setSubject(subject);
		try {
			email.setMsg(msg);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.addTo(EMAIL_TO);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.send();
		} catch (EmailException e) {
			messageErreur = "Une erreur s'est produite, veuillez r�essayer";
			e.printStackTrace();
		}
		
		return messageErreur;
	}
	

	public static String sendEmailToClient(String from, String subject, String msg, String emailTo) {
//		String userName = "culture.eventsdawan@gmail.com";
//		String password = "dawan2018";
		
		String userName = "culture.eventsdawan@gmail.com";
		String password = "dawan2018";
		
		String messageErreur = null;
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setAuthentication(userName, password);
		email.setSmtpPort(465);
		email.setSSL(true);
		
		try {
			email.setFrom(from);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		email.setSubject(subject);
		try {
			email.setMsg(msg);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.addTo(emailTo);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.send();
		} catch (EmailException e) {
			messageErreur = "Une erreur s'est produite, veuillez r�essayer";
			e.printStackTrace();
		}
		
		return messageErreur;
	}
}
