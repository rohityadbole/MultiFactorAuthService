package com.multifactauth.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service
public class EmailService {

	private static final String username="discuser.test@gmail.com";
	private static final String password = "Abc@1234";
	/*Login to Gmail.
	Access the URL as https://www.google.com/settings/security/lesssecureapps
	Select "Turn on"* for above email to work */
	
	public boolean sendEmail(String emailid, String twoFaCode) throws AddressException, MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

		MimeMessage message = new MimeMessage(session);
		//message.setFrom(new InternetAddress(username));
		message.addHeader("Content-type", "text/HTML; charset=UTF-8");
		message.addHeader("format", "flowed");
		message.addHeader("Content-Transfer-Encoding", "8bit");
		message.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
		try {
			message.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailid));
		
		 message.setSubject("Login Security code");
         message.setText("Your Two Factor Login Security Authentication code is:"+twoFaCode);
         Transport.send(message);
         return true;
	}
}