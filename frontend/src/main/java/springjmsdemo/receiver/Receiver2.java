package springjmsdemo.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import model.News;


@Component
public class Receiver2 {
	@JmsListener(destination = "topOne", containerFactory = "jmsContainerFactory")
	public void topOne(News news) {
		System.out.println("Receiver2 topOne: " + news);
	}

	@JmsListener(destination = "topTwo", containerFactory = "jmsContainerFactory")
	public void topTwo(News news) {
		System.out.println("Receiver2 topTwo: " + news);
	}

	@JmsListener(destination = "topThree", containerFactory = "jmsContainerFactory")
	public void topThree(News news) {
		System.out.println("Receiver2 topThree: " + news);
	}
}