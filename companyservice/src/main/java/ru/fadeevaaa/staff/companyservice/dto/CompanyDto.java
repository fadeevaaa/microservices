package ru.fadeevaaa.staff.companyservice.dto;

import java.util.List;

public class CompanyDto {

    private String name;
    private Double budget;
//    private List<Long> employeesId;

    public CompanyDto() {
    }

    public CompanyDto(String name, Double budget) {
        this.name = name;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

//    public List<Long> getEmployeesId() {
//        return employeesId;
//    }
//
//    public void setEmployeesId(List<Long> employeesId) {
//        this.employeesId = employeesId;
//    }
}
