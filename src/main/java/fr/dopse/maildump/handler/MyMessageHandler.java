package fr.dopse.maildump.handler;

import fr.dopse.maildump.model.EmailEntity;
import fr.dopse.maildump.service.IEmailService;
import org.simplejavamail.converter.EmailConverter;
import org.simplejavamail.email.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.RejectException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dopse on 16/04/2017.
 */

@Component
public class MyMessageHandler implements MessageHandler {

    private final static Logger log = LoggerFactory.getLogger(MyMessageHandler.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private IEmailService emailService;

    @Override
    public void from(String from) throws RejectException {
        // NOT IMPLEMENTED
    }

    @Override
    public void recipient(String recipient) throws RejectException {
        // NOT IMPLEMENTED
    }

    @Override
    public void data(InputStream data) throws RejectException, IOException {
        Email email = getEmailFromData(data);
        if(email != null) {
            EmailEntity emailEntity = emailService.addEmail(email);
            simpMessagingTemplate.convertAndSend("/email/add",
                    emailService.findEmailById(emailEntity.getId()));
        }
    }

    @Override
    public void done() {
        log.info("Email processed");
    }

    private Email getEmailFromData(InputStream data) {
        Email email = null;
        try {
            MimeMessage message = new MimeMessage(getSession(), data);
            email = EmailConverter.mimeMessageToEmail(message);
        } catch (MessagingException e) {
            log.error("Unable to convert in MimeMessage");
        }

        return email;
    }

    private Session getSession() {
        return Session.getDefaultInstance(new Properties());
    }
}
