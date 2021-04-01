package backend.model;

import lombok.Data;

import java.util.List;

@Data
public class Addressable {
    private int receiverId;
    private List<Product> products;
}
