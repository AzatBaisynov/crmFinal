package kg.itacademy.finalproject.model;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageModel {
    private String productName;
    private Integer count;
    private Integer pricePerUnit;
    private Integer totalPrice;

    @Override
    public String toString() {
        String bagSmile = EmojiParser.parseToUnicode(":moneybag:");
        String chartSmile = EmojiParser.parseToUnicode(":chart_with_upwards_trend:");
        String cashSmile = EmojiParser.parseToUnicode(":dollar:");
        String boxSmile = EmojiParser.parseToUnicode(":package:");

        return  boxSmile + "Название продукции: " + productName +
                "\n"+ chartSmile +"Количество: " + count +
                "\n"+ cashSmile + "Цена за ед. товара: " + pricePerUnit +
                "\n"+ bagSmile + "Сумма: " + totalPrice;
    }
}
