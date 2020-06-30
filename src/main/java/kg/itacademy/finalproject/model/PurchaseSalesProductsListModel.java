package kg.itacademy.finalproject.model;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.EmptyStackException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseSalesProductsListModel {
    private String productName;
    private Integer count;
    private Integer pricePerUnit;
    private String userName;
    private Integer totalPrice;
    private LocalDate date;

    @Override
    public String toString() {
        String caseSmile = EmojiParser.parseToUnicode(":briefcase:");
        String cashSmile = EmojiParser.parseToUnicode(":dollar:");
        String boxSmile = EmojiParser.parseToUnicode(":package:");
        String chartSmile = EmojiParser.parseToUnicode(":chart_with_upwards_trend:");
        String bagSmile = EmojiParser.parseToUnicode(":moneybag:");
        String dateSmile = EmojiParser.parseToUnicode(":calendar:");
        return  caseSmile + "Сотрудник: " + userName +
                "\n" + boxSmile + "Название продукции: " + productName +
                "\n"+ chartSmile +"Количество: " + count +
                "\n"+ cashSmile + "Цена за ед. товара: " + pricePerUnit +
                "\n"+ bagSmile + "Сумма: " + totalPrice +
                "\n"+ dateSmile + "Дата: " + date + "\n";
    }
}
