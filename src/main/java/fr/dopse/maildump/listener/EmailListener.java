package fr.dopse.maildump.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import fr.dopse.maildump.service.IEmailService;

import org.simplejavamail.converter.EmailConverter;
import org.simplejavamail.email.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.subethamail.smtp.helper.SimpleMessageListener;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Component(value = "emailListener")
public class EmailListener implements SimpleMessageListener {

	private final static Logger log = LoggerFactory.getLogger(EmailListener.class);

	@Autowired
	private IEmailService emailService;

	@Override
	public boolean accept(String s, String s1) {
		return true;
	}

	@Override
	public void deliver(String from, String recipient, InputStream data) throws IOException {

		log.info("Delivering mail from " + from + " to " + recipient);

		Email message = getEmailFromData(data);
		if (message != null) {
			emailService.addEmail(message);
		}
	}

	private Email getEmailFromData(InputStream data) {
		Email email = null;
		try {
			MimeMessage message = new MimeMessage(getSession(), data);
			email = EmailConverter.mimeMessageToEmail(message);
		} catch (MessagingException e) {
			log.error("Impossible de convertir en MimeMessage");
		}

		return email;
	}

	private Session getSession() {
		return Session.getDefaultInstance(new Properties());
	}
}
