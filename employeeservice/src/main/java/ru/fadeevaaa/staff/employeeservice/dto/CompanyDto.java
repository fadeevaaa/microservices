package ru.fadeevaaa.staff.employeeservice.dto;

public class CompanyDto {

//    private long id;
    private String name;
    private Double budget;

    public CompanyDto() {
    }

    public CompanyDto(String name, Double budget) {
        this.name = name;
        this.budget = budget;
    }
//    public CompanyDto(String companyName, Double companyBudget) {
//        this.companyName = companyName;
//        this.companyBudget = companyBudget;
//    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

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
}
