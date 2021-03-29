package springjmsdemo;

import javax.jms.JMSException;
import javax.jms.Topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import springjmsdemo.model.News;

@SpringBootApplication
public class Application {
	public static void main(String[] args) throws JmsException, JMSException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		Topic springTopic = jmsTemplate.getConnectionFactory().createConnection()
				.createSession().createTopic("spring");
		News news = new News(100, "Latest news on Spring");
		jmsTemplate.convertAndSend(springTopic, news);
	}
}