package fr.dopse.maildump.service;

import java.util.List;

import fr.dopse.maildump.model.EmailEntity;

import org.simplejavamail.email.Email;

/**
 * Created by fr27a86n on 11/04/2017.
 */
public interface IEmailService {

	EmailEntity addEmail(Email email);

	void deleteEmail(Long id);

	List<EmailEntity> findAllEmail();

    EmailEntity findEmailById(Long id);
}
