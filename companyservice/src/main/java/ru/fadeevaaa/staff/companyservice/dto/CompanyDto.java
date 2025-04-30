package ru.fadeevaaa.staff.companyservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {

    @NotBlank(message = "Название компании обязано быть указано.")
    private String name;
    @Positive(message = "Бюджет компании должен быть положительным числом.")
    private Double budget;
    private List<UserDto> users;
}
