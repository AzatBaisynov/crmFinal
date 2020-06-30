package kg.itacademy.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersProfitModel {
    private String fullName;
    private List<Earning> earnings;
    private Integer totalEarningsByDay;
}
