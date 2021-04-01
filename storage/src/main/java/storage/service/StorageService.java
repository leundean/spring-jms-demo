package storage.service;

import storage.amqservice.TopicSender;
import storage.config.EnvVariables;
import storage.model.CommonStatus;
import storage.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import storage.model.StorageEvent;

import java.util.*;

@Service
@EnableScheduling
public class StorageService {

    @Autowired
    EnvVariables envVariables;

    @Autowired
    StorageInventory storageInventory;

    @Autowired
    TopicSender topicSender;

    public List<Product> listAll(){
        List<Product> products = storageInventory.getAll();
        Collections.sort(products);
        return products;
    }

    public List<Product> fill(List<Product> products){
        storageInventory.fill(products);
        topicSender.send("storage.event", new StorageEvent("Added " + products.size() + " products"));
        return products;
    }

    public List<Product> getRandomProducts(int number){
        List<Product> randomList = new ArrayList<>();
        for (int i=0; i < number; i++) {
            Random random = new Random();
            int randomInt = random.nextInt(storageInventory.getSize());
            randomList.add(storageInventory.getAll().get(randomInt));
        }
        return randomList;
    }

    @Scheduled(cron = "0 0/10 * * * *")
    public void reportStatus(){
        Map<String, String> statusInfo = new HashMap<>();
        statusInfo.put("inventory", Integer.toString(storageInventory.getSize()));
        statusInfo.put("types", storageInventory.productTypes().toString());
        topicSender.send("common.status", new CommonStatus(envVariables.getSenderId(), statusInfo));
    }

}
