package sn.zone.bakcup_api.web.dto;

import lombok.Data;
import sn.zone.bakcup_api.data.entities.Product;

@Data
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String userId;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.userId = product.getUserId();
    }
}
