package storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Comparable<Product> {

    private Integer productId;
    private String productName;
    private String productType;
    private BigDecimal price;

    @Override
    public int compareTo(Product product){
        return this.getProductName().compareTo(product.getProductName());
    }

}
