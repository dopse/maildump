package fr.dopse.maildump.service.impl;

import fr.dopse.maildump.dao.IAttachmentDAO;
import fr.dopse.maildump.model.AttachmentEntity;
import fr.dopse.maildump.service.IAttachmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Service
public class AttachmentServiceImpl implements IAttachmentService {

	@Autowired
	private IAttachmentDAO attachmentDAO;

	@Override
	public AttachmentEntity findAttachmentById(Long id) {
		return attachmentDAO.findOne(id);
	}
}
