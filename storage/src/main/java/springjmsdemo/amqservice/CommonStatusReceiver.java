package springjmsdemo.amqservice;

import org.springframework.beans.factory.annotation.Autowired;
import springjmsdemo.config.EnvVariables;
import springjmsdemo.model.CommonStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import springjmsdemo.service.DistributionService;


@Slf4j
@Service
public class CommonStatusReceiver {

	/*
    	Origin: All
    	This represents broadcasted information
	 */

	@Autowired
	EnvVariables envVariables;

	private int lowLimit = 3;

	@Autowired
	DistributionService distributionService;

	@JmsListener(destination = "common.status", containerFactory = "jmsContainerFactory")
	public void receive(CommonStatus commonStatus) {
		if (commonStatus.getSenderId() != envVariables.getSenderId()) {
			log.info("Received status: " + commonStatus);
			if (Integer.parseInt(commonStatus.getStatusValues().get("inventory")) < lowLimit){
				distributionService.addresses.add(commonStatus.getSenderId());
			}
		}
	}

}