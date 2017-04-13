package fr.dopse.maildump.controller;

import java.util.List;

import fr.dopse.maildump.model.EmailEntity;
import fr.dopse.maildump.service.IEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Controller
public class EmailController {

	@Autowired
	private IEmailService emailService;

	@MessageMapping("/deleteEmail")
	@SendTo("/email/delete")
	public Long deleteEmail(Long id) {
		emailService.deleteEmail(id);
		return id;
	}

	@SubscribeMapping("/email/list")
	public List<EmailEntity> getlistEmail() {
		return emailService.findAllEmail();
	}

}
