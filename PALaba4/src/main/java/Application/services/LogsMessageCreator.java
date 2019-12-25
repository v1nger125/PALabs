package Application.services;

import Application.entities.LogsItem;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class LogsMessageCreator implements MessageCreator {
    private LogsItem history;

    public LogsMessageCreator(LogsItem history){
        this.history = history;
    }
    @Override
    public Message createMessage(Session session) throws JMSException {
        Message message = session.createObjectMessage(history);
        return message;
    }
}
