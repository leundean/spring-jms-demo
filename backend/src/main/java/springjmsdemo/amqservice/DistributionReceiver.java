package springjmsdemo.amqservice;

import springjmsdemo.config.EnvVariables;
import springjmsdemo.model.Addressable;
import springjmsdemo.service.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/*
    Origin: Storage
    This represents delivery of goods and uses the broadcast pattern with a filter
 */

@Slf4j
@Service
public class DistributionReceiver {

    @Autowired
    EnvVariables envVariables;

    @Autowired
    Inventory inventory;

    @JmsListener(destination = "distribution", containerFactory = "jmsContainerFactory")
    public void receive(Addressable addressable) {
        log.info("Received status: " + addressable);
        if (addressable.getReceiverId() == envVariables.getSenderId()){
            inventory.addProducts(addressable.getProducts());
        }
    }

}