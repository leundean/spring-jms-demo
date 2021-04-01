package springjmsdemo.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import model.News;


@Component
public class Receiver1 {
	@JmsListener(destination = "topOne", containerFactory = "jmsContainerFactory")
	public void topOne(News news) {
		System.out.println("Receiver1 topOne: " + news.getExtra().get(0).getTitle());
	}
/*
	@JmsListener(destination = "topTwo", containerFactory = "jmsContainerFactory")
	public void topTwo(News news) {
		System.out.println("Receiver1 topTwo: " + news.toString());
	}

	@JmsListener(destination = "topThree", containerFactory = "jmsContainerFactory")
	public void topThree(News news) {
		System.out.println("Receiver1 topThree: " + news.getTitle());
	}
*/
}
