package kg.itacademy.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Earning {
    private String productName;
    private Integer earning;
    private LocalDateTime time;
    private String imagePath;

    public Earning(String productName, Integer earning, LocalDateTime time) {
        this.productName = productName;
        this.earning = earning;
        this.time = time;
    }


}
