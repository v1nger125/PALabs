package Application;

import Application.entities.LogsItem;
import Application.repositories.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class ReceiveMessage implements SessionAwareMessageListener<ObjectMessage> {

    @Autowired
    private LogsRepository repository;

    @Override
    public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {
        if(objectMessage.getObject() instanceof LogsItem) {
            LogsItem logsItem = (LogsItem) objectMessage.getObject();
            repository.save(logsItem);
        }
    }
}

