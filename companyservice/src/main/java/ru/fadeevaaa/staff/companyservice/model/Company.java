package ru.fadeevaaa.staff.companyservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private Double budget;

    @ElementCollection(fetch=FetchType.EAGER)
    private Set<Long> employeesId = new HashSet<>();
}
