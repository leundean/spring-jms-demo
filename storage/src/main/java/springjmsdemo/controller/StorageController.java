package springjmsdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springjmsdemo.model.Product;
import springjmsdemo.service.StorageService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class StorageController {

    @Autowired
    StorageService storageService;

    @GetMapping(value = "/listall")
    @ResponseBody
    public List<Product> listProducts(){
        log.info("/listall");
        return storageService.listAll();
    }

    @PostMapping(value = "/fill")
    @ResponseBody
    public List<Product> fill(@RequestBody List<Product> products){
        log.info("/fill");
        storageService.fill(products);
        return products;
    }

    @GetMapping(value = "/produce")
    @ResponseBody
    public List<Product> produce(){
        log.info("/produce");
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(null, "Apple", "Fruit", new BigDecimal(4.25)));
        productList.add(new Product(null, "Apple", "Fruit", new BigDecimal(4.25)));
        productList.add(new Product(null, "Kiwifruit", "Fruit", new BigDecimal(3.25)));
        productList.add(new Product(null, "Kiwifruit", "Fruit", new BigDecimal(3.25)));
        productList.add(new Product(null, "BMW", "Car", new BigDecimal(400000.0)));
        productList.add(new Product(null, "BMW", "Car", new BigDecimal(500000.0)));
        productList.add(new Product(null, "Sunchair", "Furniture", new BigDecimal(50.0)));
        productList.add(new Product(null, "Waterbottle", "Water", new BigDecimal(1.0)));
        productList.add(new Product(null, "Watertank", "Water", new BigDecimal(10.5)));
        productList.add(new Product(null, "Costume", "Clothes", new BigDecimal(20.0)));
        storageService.fill(productList);
        return productList;
    }

}