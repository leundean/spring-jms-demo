package springjmsdemo.sender;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import model.CommonStatus;
import model.ExtraNews;
import model.News;

import javax.jms.JMSException;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties
        //(prefix = "activemq")
@Component
public class TopicSender implements InitializingBean {

    public static int messageCount = 0;

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

    public void send(String topicName, String message){
        News news = new News(messageCount, message, List.of(new ExtraNews(messageCount, message)));
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("a", "A");
        infoMap.put("b", "B");
        CommonStatus commonStatus = new CommonStatus((int)Math.floor(Math.random()*10), infoMap);
        for (Topic topic :  topics){
            try {
                if (topic.getTopicName().equals(topicName)){
                    jmsTemplate.convertAndSend(topic, commonStatus);
                    messageCount++;
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
