package fr.dopse.maildump.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import fr.dopse.maildump.model.AttachmentEntity;
import fr.dopse.maildump.model.EmailEntity;
import fr.dopse.maildump.service.IAttachmentService;
import fr.dopse.maildump.service.IEmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@RestController
public class EmailController {

	private final static Logger log = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private IEmailService emailService;

	@Autowired
	private IAttachmentService attachmentService;

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

	@RequestMapping(value = "/email/attachment/{id}",method = RequestMethod.GET)
	public void getAttachmentContent(@PathVariable("id") Long id, HttpServletResponse response) {

		final AttachmentEntity attachment = attachmentService.findAttachmentById(id);

		if (attachment != null) {
			try {
				ServletOutputStream stream = response.getOutputStream();
				OutputStream out = new BufferedOutputStream(stream);

				response.resetBuffer();
				response.setBufferSize(attachment.getAttachmentContent().getData().length);
				response.setHeader("Content-Length", String.valueOf(attachment.
                        getAttachmentContent().getData().length));

				out.write(attachment.getAttachmentContent().getData());

				out.close();
				stream.close();
			} catch (final IOException e) {
				log.error("Unable to open file", e);
			}
		} else {
			log.error("File does not exist");
		}
	}
}
