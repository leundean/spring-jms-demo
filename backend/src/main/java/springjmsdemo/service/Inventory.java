package springjmsdemo.service;

import springjmsdemo.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Inventory {

    private List<Product> products = new ArrayList<>();

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

    public List<Product> getProductsByType(String productType) {
        return products.stream().filter(p -> p.getProductType().equals(productType)).collect(Collectors.toList());
    }

    public List<Product> getAll() {
        return products;
    }

    public void addProducts(List<Product> newProducts) {
        products.addAll(newProducts);
    }

    public Product removeProductById(int productId) {
        Product removedProduct = getProductById(productId);
        products.removeIf(p -> removedProduct.getProductId() == p.getProductId());
        return removedProduct;
    }
}
