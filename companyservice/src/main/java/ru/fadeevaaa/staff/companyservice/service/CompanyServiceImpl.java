package ru.fadeevaaa.staff.companyservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fadeevaaa.staff.companyservice.dto.CompanyDto;
import ru.fadeevaaa.staff.companyservice.dto.UserDto;
import ru.fadeevaaa.staff.companyservice.mapper.CompanyMapper;
import ru.fadeevaaa.staff.companyservice.model.Company;
import ru.fadeevaaa.staff.companyservice.repository.CompanyRepository;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final APIClient apiClient;
    private final CompanyMapper mapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, APIClient apiClient,
                              @Qualifier("companyMapperImpl") CompanyMapper mapper) {
        this.companyRepository = companyRepository;
        this.apiClient = apiClient;
        this.mapper = mapper;
    }

    @Override
    public CompanyDto create(Company company) {
        Company newCompany = companyRepository.save(company);
        CompanyDto companyDto = mapper.toDto(newCompany);

        List<UserDto> usersDto = apiClient.getAllUsersByCompanyId(company.getId());
        companyDto.setUsers(usersDto);
        log.info("Компания {} успешно создана", newCompany);
        return companyDto;
    }

    @Override
    public CompanyDto getCompanyById(long id) {
        Company company = companyRepository.findById(id).get();
        CompanyDto companyDto = mapper.toDto(company);

        List<UserDto> usersDto = apiClient.getAllUsersByCompanyId(company.getId());
        companyDto.setUsers(usersDto);
        log.info("По ID {} возвращена компания {}", id, company);
        return companyDto;
    }

    @Override
    public CompanyDto updateCompany(long id, Company updatedCompany) {
        updatedCompany.setId(id);
        Company updatedSavedCompany = companyRepository.save(updatedCompany);
        CompanyDto companyDto = mapper.toDto(updatedCompany);

        List<UserDto> usersDto = apiClient.getAllUsersByCompanyId(updatedCompany.getId());
        companyDto.setUsers(usersDto);
        log.info("Данные о компании с ID {} успешно изменены на {}", id, updatedSavedCompany);
        return companyDto;
    }

    @Override
    public ResponseEntity<CompanyDto> deleteCompany(long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyRepository.deleteById(id);
        log.info("Компания с ID {} успешно удалена", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Page<CompanyDto> getAllCompanies(Integer offset, Integer limit) {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDto> companiesDto = companies.stream().map(company -> {
            CompanyDto companyDto = mapper.toDto(company);
            List<UserDto> usersDto = apiClient.getAllUsersByCompanyId(company.getId());
            companyDto.setUsers(usersDto);
            return companyDto;
        }).toList();

        Pageable pageable = PageRequest.of(offset, limit);
        long totalSize = companiesDto.size();
        Page<CompanyDto> resultPage = new PageImpl<>(companiesDto, pageable, totalSize);
        log.info("Возвращен список всех компаний");
        return resultPage;
    }
}
