package com.jobster.server.util;

import com.jobster.business.BLL.Constantes;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class Email {
	public static void sendEmail(String toEmail, String subject, String body) {
		//creamos la session
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", Constantes.SRV_EMAIL_HOST); //SMTP Host
		props.put("mail.smtp.socketFactory.port", Constantes.SRV_EMAIL_PORT); //SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", Constantes.SRV_EMAIL_PORT); //SMTP Port

		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constantes.SRV_EMAIL_USR, Constantes.SRV_EMAIL_PWD);
			}
		};

		Session session = Session.getInstance(props, auth);
		sendEmail(session, toEmail, subject, body);

		//****************************************
		//sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");
		//sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
	}


	private static void sendEmail(Session session, String toEmail, String subject, String body){
		try
		{
			MimeMessage msg = createMimeMessage(session, toEmail,subject);
			msg.setContent(body, "text/html; charset=utf-8");

			System.out.println("Message is ready");
			Transport.send(msg);
			System.out.println("EMail Sent Successfully!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static MimeMessage createMimeMessage(Session session, String toEmail, String subject){
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(Constantes.SRV_EMAIL_FROM_ACCOUNT, "Jobster"));

			msg.setReplyTo(InternetAddress.parse(Constantes.SRV_EMAIL_FROM_ACCOUNT, false));

			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return  msg;
	}
	public enum TipoFormatoFichero {
		None,
		Binario,
		Plano
    }
}
