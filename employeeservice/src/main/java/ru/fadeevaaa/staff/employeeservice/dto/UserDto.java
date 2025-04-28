package ru.fadeevaaa.staff.employeeservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Имя обязательно должно быть заполнено.")
    private String firstName;
    @NotBlank(message = "Фамилия должна быть указана.")
    private String lastName;
    @Pattern(regexp="^\\+?[1-9]\\d{1,14}$",
            message = "Телефон должен соответствовать международному формату (+XXXXXXXXXXX)")
    private String phoneNumber;
    private CompanyDto company;
}
