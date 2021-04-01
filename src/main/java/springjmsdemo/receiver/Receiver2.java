package springjmsdemo.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import model.CommonStatus;


@Component
public class Receiver2 {
	@JmsListener(destination = "topTwo", containerFactory = "jmsContainerFactory")
	public void topOne(CommonStatus commonStatus) {
		System.out.println("Receiver2 topTwo: " + commonStatus.getSenderId());
	}
}