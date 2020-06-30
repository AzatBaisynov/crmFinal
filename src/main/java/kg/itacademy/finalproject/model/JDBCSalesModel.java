package kg.itacademy.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JDBCSalesModel {
    private String name;
    private List<JDBCRevenueModel> list;
    private Integer total;
}
