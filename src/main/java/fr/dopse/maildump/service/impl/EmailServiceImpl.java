package fr.dopse.maildump.service.impl;

import java.util.List;

import fr.dopse.maildump.converter.EmailConverter;
import fr.dopse.maildump.dao.IEmailDAO;
import fr.dopse.maildump.model.EmailEntity;
import fr.dopse.maildump.service.IEmailService;

import org.simplejavamail.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private EmailConverter emailConverter;

	@Autowired
	private IEmailDAO emailDAO;

	@Override
    @Transactional
	public EmailEntity addEmail(Email email) {

		EmailEntity emailEntity = emailConverter.convert(email);
        return emailDAO.save(emailEntity);
	}

	@Override
    @Transactional
	public void deleteEmail(Long id) {
		emailDAO.delete(id);
	}

	@Override
    @Transactional(readOnly = true)
	public List<EmailEntity> findAllEmail() {
		return emailDAO.findAll();
	}

    @Override
    @Transactional(readOnly = true)
    public EmailEntity findEmailById(Long id) {
        return emailDAO.findOne(id);
    }
}
