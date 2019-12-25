package Application;

import Application.entities.EmailHistory;
import Application.repositories.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class MailReceiver implements SessionAwareMessageListener<ObjectMessage> {
    @Autowired
    private EmailHistoryRepository repository;

    @Autowired
    private JavaMailSender sender;

    @Override
    public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {
        if(objectMessage.getObject() instanceof EmailHistory) {
            EmailHistory history = (EmailHistory) objectMessage.getObject();
            repository.save(history);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Me");
            mailMessage.setSubject("Alarm");
            mailMessage.setText(history.getCondition());
            mailMessage.setTo(history.getEmail());
            sender.send(mailMessage);
        }

    }
}
