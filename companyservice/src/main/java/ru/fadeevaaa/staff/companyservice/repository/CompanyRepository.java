package ru.fadeevaaa.staff.companyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fadeevaaa.staff.companyservice.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
