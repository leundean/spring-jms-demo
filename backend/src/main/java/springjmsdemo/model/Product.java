package springjmsdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Product implements Comparable<Product> {

    private int productId;
    private String productName;
    private String productType;
    private BigDecimal price;

    @Override
    public int compareTo(Product product){
        return this.getProductName().compareTo(product.getProductName());
    }

}
