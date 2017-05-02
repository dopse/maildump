package fr.dopse.maildump.converter;

import fr.dopse.maildump.model.EmailEntity;

import org.simplejavamail.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Component
public class EmailConverter implements Converter<Email, EmailEntity> {

	@Autowired
	private RecipientConverter recipientConverter;

	@Autowired
	private AttachmentConverter attachmentConverter;

	@Override
	public EmailEntity convert(Email email) {
		EmailEntity emailEntity = new EmailEntity();
		emailEntity.setName(email.getFromRecipient().getName());
		emailEntity.setEmail(email.getFromRecipient().getAddress());
		emailEntity.setRecipients(recipientConverter.convertList(email.getRecipients()));
		emailEntity.setAttachments(attachmentConverter.convertList(email.getAttachments()));
		emailEntity.setSubject(email.getSubject());
		emailEntity.setContent(email.getTextHTML());
		emailEntity.setDate(new Date());

		return emailEntity;
	}
}
