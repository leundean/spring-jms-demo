package storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Addressable {
    private int receiverId;
    private List<Product> products;
}
