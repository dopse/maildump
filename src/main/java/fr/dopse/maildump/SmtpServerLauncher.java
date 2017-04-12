package fr.dopse.maildump;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.helper.SimpleMessageListener;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Service
public class SmtpServerLauncher {

	private final static Logger log = LoggerFactory.getLogger(SmtpServerLauncher.class);

	@Value("${smtpserver.hostName:localhost}")
	String hostName;

	@Value("${smtpserver.port:25}")
	int port;

	@Autowired
	@Qualifier(value = "emailListener")
	private SimpleMessageListener simpleMessageListener;

	private SMTPServer smtpServer;

	@PostConstruct
	public void start() {
		smtpServer = new SMTPServer(new SimpleMessageListenerAdapter(simpleMessageListener));
		smtpServer.setPort(port);
		smtpServer.setHostName(hostName);
		smtpServer.start();

		log.info("****** SMTP Server is running for domain " + smtpServer.getHostName()
				+ " on port " + smtpServer.getPort());
	}

	@PreDestroy
	public void stop() {
		log.info("****** Stopping SMTP Server for domain " + smtpServer.getHostName() + " on port "
				+ smtpServer.getPort());
		smtpServer.stop();
	}

}
