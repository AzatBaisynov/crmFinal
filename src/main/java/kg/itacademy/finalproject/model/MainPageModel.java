package kg.itacademy.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainPageModel {
    private List<UsersProfitModel> usersProfitModels;
    private List<ProductsModel> productsModels;
}
