package ru.fadeevaaa.staff.companyservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fadeevaaa.staff.companyservice.dto.CompanyDto;
import ru.fadeevaaa.staff.companyservice.dto.ResponseDto;
import ru.fadeevaaa.staff.companyservice.dto.UserDto;
import ru.fadeevaaa.staff.companyservice.model.Company;
import ru.fadeevaaa.staff.companyservice.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final APIClient apiClient;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, APIClient apiClient) {
        this.companyRepository = companyRepository;
        this.apiClient = apiClient;
    }

    private CompanyDto mapToCompanyDto (Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setBudget(company.getBudget());
        return companyDto;
    }

    @Override
    public ResponseDto create(Company company) {
        ResponseDto responseDto = new ResponseDto();
        Company newCompany = companyRepository.save(company);
        CompanyDto companyDto = mapToCompanyDto(newCompany);

        List<UserDto> usersDto= apiClient.getAllUsersByCompanyId(company.getId());
        responseDto.setUsers(usersDto);
        responseDto.setCompany(companyDto);

        return responseDto;
    }

    @Override
    public ResponseDto getCompanyById(long id) {
        ResponseDto responseDto = new ResponseDto();
        Company company = companyRepository.findById(id).get();
        CompanyDto companyDto = mapToCompanyDto(company);

        List<UserDto> usersDto = apiClient.getAllUsersByCompanyId(company.getId());
        responseDto.setUsers(usersDto);
        responseDto.setCompany(companyDto);

        return responseDto;
    }

    @Override
    public ResponseDto updateCompany(long id, Company updatedCompany) {
        updatedCompany.setId(id);
        Company updatedSavedCompany = companyRepository.save(updatedCompany);
        ResponseDto responseDto = new ResponseDto();
        CompanyDto companyDto = mapToCompanyDto(updatedCompany);

        List<UserDto> usersDto = apiClient.getAllUsersByCompanyId(updatedCompany.getId());
        responseDto.setUsers(usersDto);
        responseDto.setCompany(companyDto);

        return responseDto;
    }

    @Override
    public ResponseEntity<Company> deleteCompany(long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<ResponseDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<ResponseDto> responsesDto = companies.stream().map(company -> {
            ResponseDto responseDto = new ResponseDto();
            CompanyDto companyDto = mapToCompanyDto(company);
            List<UserDto> usersDto = apiClient.getAllUsersByCompanyId(company.getId());
            responseDto.setUsers(usersDto);
            responseDto.setCompany(companyDto);
            return responseDto;
        }).toList();

        return responsesDto;
    }
}
