package kg.itacademy.finalproject.model;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JDBCRevenueModel {
    private Timestamp date;
    private Integer revenue;
    private String productName;
    private String userFullName;


    @Override
    public String toString() {
        String timeSmile = EmojiParser.parseToUnicode(":clock3:");
        String cashSmile = EmojiParser.parseToUnicode(":dollar:");
        String boxSmile = EmojiParser.parseToUnicode(":package:");
        int minutes = date.getMinutes();
        String min = "";
        if (minutes < 10){
            min = "0" + minutes;
        } else {
            min = minutes + "";
        }
        return  boxSmile + "Название продукции: " + productName +
                "\n"+cashSmile+"Прибыль:" + revenue +
                "\n"+timeSmile+"Время " + date.getHours() + ":" +  min + "\n\n";

    }
}
