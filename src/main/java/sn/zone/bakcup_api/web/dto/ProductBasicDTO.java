package sn.zone.bakcup_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zone.bakcup_api.data.entities.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBasicDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero(message = "Price must be greater or equals to 0")
    private Double price;

    public Product toProduct(String userId) {
        return new Product(name, description, price, userId);
    }
}
