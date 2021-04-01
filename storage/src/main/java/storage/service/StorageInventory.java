package storage.service;

import storage.model.Product;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StorageInventory {

    private int nextId = 0;

    private List<Product> products = new ArrayList<>();

    public List<Product> getAll(){
        Collections.sort(products);
        return products;
    }

    public Product getProductById(int productId){
        for (Product product : products){
            if (product.getProductId() == productId){
                return product;
            }
        }
        return null;
    }

    public int getSize(){
        return products.size();
    }

    public Set<String> productTypes(){
        Set<String> productTypes = new HashSet<>();
        for (Product product : products){
            productTypes.add(product.getProductType());
        }
        return productTypes;
    }

    public void fill(List<Product> products) {
        for (Product product : products){
            if (product.getProductId() == null){
                product.setProductId(nextId);
                nextId++;
            }
        }
        this.products.addAll(products);
    }

}
