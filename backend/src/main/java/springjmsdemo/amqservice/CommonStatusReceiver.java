package springjmsdemo.amqservice;

import springjmsdemo.config.EnvVariables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import springjmsdemo.model.CommonStatus;

@Slf4j
@Service
public class CommonStatusReceiver {

	/*
    	Origin: Any
    	This represents broadcasted information
	 */

	@Autowired
	EnvVariables envVariables;

	@JmsListener(destination = "common.status", containerFactory = "jmsContainerFactory")
	public void receive(CommonStatus commonStatus) {
		if (commonStatus.getSenderId() != envVariables.getSenderId()) {
			log.info("Received status: " + commonStatus.getSenderId() + ": " + commonStatus.getStatusValues().toString());
		}
	}

}