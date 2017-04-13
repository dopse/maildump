package fr.dopse.maildump.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.dopse.maildump.model.AttachmentEntity;

import org.apache.commons.io.IOUtils;
import org.simplejavamail.email.AttachmentResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Component
public class AttachmentConverter implements Converter<AttachmentResource, AttachmentEntity> {

	private final static Logger log = LoggerFactory.getLogger(AttachmentConverter.class);

	@Override
	public AttachmentEntity convert(AttachmentResource attachment) {
		AttachmentEntity attachmentEntity = new AttachmentEntity();
		attachmentEntity.setName(attachment.getName());
		attachmentEntity.setContentType(attachment.getDataSource().getContentType());
		try {
			attachmentEntity.setData(IOUtils.toByteArray(attachment.getDataSource().getInputStream()));
		} catch (IOException e) {
			log.error("Unable to read content of attachment");
		}

		return attachmentEntity;
	}

	List<AttachmentEntity> convertList(List<AttachmentResource> attachments) {
		List<AttachmentEntity> attachmentEntityList = new ArrayList<>();
		for (AttachmentResource attachment : attachments) {
			attachmentEntityList.add(convert(attachment));
		}

		return attachmentEntityList;
	}
}
