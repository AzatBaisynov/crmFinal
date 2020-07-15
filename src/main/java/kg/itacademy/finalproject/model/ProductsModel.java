package kg.itacademy.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsModel {
    private String productName;
    private Integer countInStorage;
    private Integer totalPrice;
    private String imagePath;

    public ProductsModel(String productName, Integer countInStorage, Integer totalPrice) {
        this.productName = productName;
        this.countInStorage = countInStorage;
        this.totalPrice = totalPrice;
    }
}
