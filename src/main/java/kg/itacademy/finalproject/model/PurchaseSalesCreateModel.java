package kg.itacademy.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseSalesCreateModel {
    private String product;
    private Integer count;
    private Integer pricePerUnit;
}
