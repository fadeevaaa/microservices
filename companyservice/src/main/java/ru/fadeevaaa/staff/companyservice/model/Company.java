package ru.fadeevaaa.staff.companyservice.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private Double budget;

    public Company(long id, String name, Double budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    public Company() {
    }

//    @Transient
//    private List<Long> employeesId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
