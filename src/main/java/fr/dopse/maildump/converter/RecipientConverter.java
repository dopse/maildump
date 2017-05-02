package fr.dopse.maildump.converter;

import java.util.ArrayList;
import java.util.List;

import fr.dopse.maildump.model.RecipientEntity;

import org.simplejavamail.email.Recipient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Component
public class RecipientConverter implements Converter<Recipient, RecipientEntity> {

	@Override
	public RecipientEntity convert(Recipient recipient) {
		RecipientEntity recipientEntity = new RecipientEntity();
		recipientEntity.setEmail(recipient.getAddress());
		recipientEntity.setName(recipient.getName());

		return recipientEntity;
	}

    List<RecipientEntity> convertList(List<Recipient> recipients) {
		List<RecipientEntity> recipientEntityList = new ArrayList<>();
		for (Recipient recipient : recipients) {
			recipientEntityList.add(convert(recipient));
		}

		return recipientEntityList;
	}
}
