package springjmsdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import springjmsdemo.amqservice.TopicSender;
import springjmsdemo.model.Addressable;

import java.util.*;

@Service
public class DistributionService {

    public Set<Integer> addresses = new HashSet<>();

    @Autowired
    TopicSender topicSender;

    @Autowired
    StorageService storageService;

    @Scheduled(cron = "0 0 6 * * *")
    public void distribute(){
        for (int address : addresses){
            topicSender.send("distribution", new Addressable(address, storageService.getRandomProducts(2)));
        }
        addresses.clear();
    }
}
