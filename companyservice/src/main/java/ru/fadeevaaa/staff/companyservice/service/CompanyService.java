package ru.fadeevaaa.staff.companyservice.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fadeevaaa.staff.companyservice.dto.CompanyDto;
import ru.fadeevaaa.staff.companyservice.model.Company;

@Service
public interface CompanyService {

    CompanyDto create(Company company);

    CompanyDto getCompanyById(long id);

    CompanyDto updateCompany(long id, Company updatedCompany);

    ResponseEntity<CompanyDto> deleteCompany(long id);

    Page<CompanyDto> getAllCompanies(Integer offset, Integer limit);
}
