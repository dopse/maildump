package fr.dopse.maildump.converter;

import fr.dopse.maildump.model.EmailEntity;

import org.simplejavamail.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Component
public class EmailConverter implements Converter<Email, EmailEntity> {

	@Autowired
	private RecipientConverter recipientConverter;

	@Override
	public EmailEntity convert(Email email) {
		EmailEntity emailEntity = new EmailEntity();
		emailEntity.setSenderName(email.getFromRecipient().getName());
		emailEntity.setSenderAddress(email.getFromRecipient().getAddress());
		emailEntity.setRecipients(recipientConverter.convertList(email.getRecipients()));
		emailEntity.setSubject(email.getSubject());
		emailEntity.setContent(email.getTextHTML());

		return emailEntity;
	}
}
