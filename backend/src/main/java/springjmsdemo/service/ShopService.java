package springjmsdemo.service;

import springjmsdemo.amqservice.TopicSender;
import springjmsdemo.config.EnvVariables;
import springjmsdemo.model.CommonStatus;
import springjmsdemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@EnableScheduling
public class ShopService {

    @Autowired
    EnvVariables envVariables;

    @Autowired
    Inventory inventory;

    @Autowired
    TopicSender topicSender;

    private static BigDecimal balance = new BigDecimal(0);

    private static int sales = 0;

    private static Set<String> visitors = new HashSet<>();

    public void registerVisit(String string){
        visitors.add(string);
    }

    public List<Product> listProducts(String productType){
        List<Product> products = inventory.getProductsByType(productType);
        Collections.sort(products);
        return products;
    }

    public Product sell(int productId){
        Product product = inventory.removeProductById(productId);
        balance = balance.add(product.getPrice());
        sales++; // Total -> Daily?
        reportStatus();
        return product;
    }

    @Scheduled(cron = "0 0/10 * * * *")
    public void reportStatus(){
        Map<String, String> statusInfo = new HashMap<>();
        statusInfo.put("inventory", Integer.toString(inventory.getSize()));
        statusInfo.put("types", inventory.productTypes().toString());
        statusInfo.put("balance", balance.toString());
        statusInfo.put("visitors", Integer.toString(visitors.size()));
        statusInfo.put("sales", Integer.toString(sales));
        topicSender.send("common.status", new CommonStatus(envVariables.getSenderId(), statusInfo));
    }

}
