package springjmsdemo.amqservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import springjmsdemo.model.StorageEvent;

@Slf4j
@Service
public class StorageEventReceiver {

	/*
    	Origin: Storage
    	This represents broadcasted information
	 */

	@JmsListener(destination = "storage.event", containerFactory = "jmsContainerFactory")
	public void receive(StorageEvent storageEvent) {
		log.info("Received event: " + storageEvent.getInfo());
	}

}