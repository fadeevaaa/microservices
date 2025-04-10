package ru.fadeevaaa.staff.companyservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fadeevaaa.staff.companyservice.dto.ResponseDto;
import ru.fadeevaaa.staff.companyservice.model.Company;

import java.util.List;

@Service
public interface CompanyService {

    ResponseDto create(Company company);

    ResponseDto getCompanyById(long id);

    ResponseDto updateCompany(long id, Company updatedCompany);

    ResponseEntity<Company> deleteCompany(long id);

    List<ResponseDto> getAllCompanies();
}
