package fr.dopse.maildump.service.impl;

import java.util.List;

import fr.dopse.maildump.converter.EmailConverter;
import fr.dopse.maildump.dao.IEmailDAO;
import fr.dopse.maildump.model.EmailEntity;
import fr.dopse.maildump.service.IEmailService;

import org.simplejavamail.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private EmailConverter emailConverter;

	@Autowired
	private IEmailDAO emailDAO;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Override
	public void addEmail(Email email) {

		EmailEntity emailEntity = emailConverter.convert(email);

		emailDAO.save(emailEntity);
		simpMessagingTemplate.convertAndSend("/email/add", emailEntity);
	}

	@Override
	public void deleteEmail(Long id) {
		emailDAO.delete(id);
	}

	@Override
	public List<EmailEntity> findAllEmail() {
		return emailDAO.findAll();
	}
}
