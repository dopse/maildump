package fr.dopse.maildump.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;

/**
 * Created by dopse on 16/04/2017.
 */

@Component
public class MyMessageHandlerFactory implements MessageHandlerFactory {

    @Autowired
    private MyMessageHandler myMessageHandler;

    @Override
    public MessageHandler create(MessageContext ctx) {
        return myMessageHandler;
    }
}
