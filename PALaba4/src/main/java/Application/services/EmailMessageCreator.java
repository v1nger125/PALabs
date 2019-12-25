package Application.services;

import Application.entities.EmailHistory;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class EmailMessageCreator implements MessageCreator {
    private EmailHistory history;

    public EmailMessageCreator(EmailHistory history){
        this.history = history;
    }
    @Override
    public Message createMessage(Session session) throws JMSException {
        Message message = session.createObjectMessage(history);
        return message;
    }

    public EmailHistory getHistory() {
        return history;
    }

    public void setHistory(EmailHistory history) {
        this.history = history;
    }
}
