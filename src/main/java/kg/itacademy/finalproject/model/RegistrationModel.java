package kg.itacademy.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationModel {
    private String companyName;
    private String login;
    private String password;
    private String fullName;
    private LocalDate age;
    private String position;
    private String telephone;
}
