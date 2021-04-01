package springjmsdemo.amqservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties
@Slf4j
@Component
public class TopicSender implements InitializingBean {

    private List<Topic> topics = new ArrayList<>();

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${activemq.topic-names}")
    private String topicNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (String topic : topicNames.split(",")){
            addTopic(topic);
        }
    }

    public void addTopic(String topicName){
        try {
            topics.add(jmsTemplate.getConnectionFactory().createConnection()
                    .createSession().createTopic(topicName));
        }
        catch (JmsException | JMSException jmsException){
            jmsException.printStackTrace();
        }
    }

    public void send(String topicName, Object object){
        for (Topic topic :  topics){
            try {
                if (topic.getTopicName().equals(topicName)){
                    jmsTemplate.convertAndSend(topic, object);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
