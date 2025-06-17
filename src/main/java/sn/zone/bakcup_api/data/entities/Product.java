package sn.zone.bakcup_api.data.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "product")
public class Product extends AbstractType {
    private String description;
    private Double price;
    private String userId;

    public Product(String name, String description, Double price, String userId) {
        super(name);
        this.description = description;
        this.price = price;
        this.userId = userId;
    }
}
