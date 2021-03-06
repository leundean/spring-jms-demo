package springjmsdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class JMSConfig {

	// Environment
	// Application name + _SERVICE_HOST
	@Value("${AMQBINARY2_SERVICE_HOST}")
	private String brokerUrl;

	// application.yml
	@Value("${activemq.openwire-port}")
	private String port;

	@Bean
	public DefaultJmsListenerContainerFactory jmsContainerFactory() {
		DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
		containerFactory.setPubSubDomain(true);
		containerFactory.setConnectionFactory(connectionFactory());
		containerFactory.setMessageConverter(jacksonJmsMessageConverter());
		return containerFactory;
	}
	@Bean
	public CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory cachConnectionFactory = new CachingConnectionFactory();
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("tcp://" + brokerUrl + ":" + port);
		cachConnectionFactory.setTargetConnectionFactory(connectionFactory);
		return cachConnectionFactory;
	}
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}