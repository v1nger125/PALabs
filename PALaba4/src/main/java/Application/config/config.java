package Application.config;

import Application.MailReceiver;
import Application.ReceiveMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.validation.constraints.NotNull;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan
public class config extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver getXSLTViewResolver(){
        XsltViewResolver xsltViewResolver = new XsltViewResolver();
        xsltViewResolver.setPrefix("/WEB-INF/xsl/");
        xsltViewResolver.setSuffix(".xslt");
        return xsltViewResolver;
    }

    @Bean(name = "dbListenerContainer")
    @Autowired
    public DefaultMessageListenerContainer dbListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
        final DefaultMessageListenerContainer defaultMessageListenerContainer = getDefaultMessageListenerContainer(connectionFactory, destination);
        defaultMessageListenerContainer.setMessageListener(receiveMessage());
        return defaultMessageListenerContainer;
    }

    @Bean(name = "emailListenerContainer")
    @Autowired
    public DefaultMessageListenerContainer emailListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
        final DefaultMessageListenerContainer defaultMessageListenerContainer = getDefaultMessageListenerContainer(connectionFactory, destination);
        defaultMessageListenerContainer.setMessageListener(emailReceiveMessage());
        return defaultMessageListenerContainer;
    }

    @NotNull
    private DefaultMessageListenerContainer getDefaultMessageListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
        final DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setDestination(destination);
        return defaultMessageListenerContainer;
    }

    @Bean
    public CachingConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connectionFactory.setTrustAllPackages(true);
        CachingConnectionFactory factory = new CachingConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public SimpleJmsListenerContainerFactory containerFactory(){
        SimpleJmsListenerContainerFactory containerFactory = new SimpleJmsListenerContainerFactory();
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        containerFactory.setConnectionFactory(factory);
        return containerFactory;
    }

    @Bean
    public ActiveMQTopic destination(){
        ActiveMQTopic topic = new ActiveMQTopic("topic");
        return topic;
    }

    @Bean
    public ReceiveMessage receiveMessage(){
        return new ReceiveMessage();
    }

    @Bean
    public MailReceiver emailReceiveMessage(){
        return new MailReceiver();
    }

    @Bean
    public JmsTemplate template(final ConnectionFactory connectionFactory, final Destination destination){
        JmsTemplate template = new JmsTemplate();
        template.setDefaultDestination(destination);
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("dmtrbrln@gmail.com");
        mailSender.setPassword("");

        final Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.ssl.trust", "smtp.gmail.com");

        return mailSender;
    }
}
