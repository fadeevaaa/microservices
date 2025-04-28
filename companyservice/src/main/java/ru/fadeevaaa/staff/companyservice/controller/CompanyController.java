package ru.fadeevaaa.staff.companyservice.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fadeevaaa.staff.companyservice.dto.CompanyDto;
import ru.fadeevaaa.staff.companyservice.mapper.CompanyMapper;
import ru.fadeevaaa.staff.companyservice.service.CompanyService;

@Validated
@Slf4j
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper mapper;

    @Autowired
    public CompanyController(CompanyService companyService, CompanyMapper mapper) {
        this.companyService = companyService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CompanyDto> addCompany(@Valid @RequestBody CompanyDto company) {
        log.info("Создание компании: {}", mapper.fromDto(company));
        CompanyDto companyDto = companyService.create(mapper.fromDto(company));
        return ResponseEntity.ok(companyDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") @Min(1) long id) {
        log.info("Нахождение компании по ID {}", id);
        CompanyDto companyDto = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<CompanyDto> getUserCompanyById(@PathVariable("id") @Min(1) long id) {
        log.info("Нахождение компании пользователя по ID {}", id);
        CompanyDto companyDto = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") @Min(1) long id,
                                                     @Valid @RequestBody CompanyDto updatedCompanyDto) {
        log.info("Обновление компании по ID {}, обновленные данные: {}", id, mapper.fromDto(updatedCompanyDto));
        CompanyDto companyDto = companyService.updateCompany(id, mapper.fromDto(updatedCompanyDto));
        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") @Min(1) long id) {
        log.info("Удаление компании по ID {}", id);
        return companyService.deleteCompany(id);
    }

    @GetMapping
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(@RequestParam("offset") @Min(0) Integer offset,
                                            @RequestParam("limit") @Min(0) Integer limit) {
        log.info("Получение списка всех компаний");
        Page<CompanyDto> companiesPage = companyService.getAllCompanies(offset, limit);
        return ResponseEntity.ok(companiesPage);
    }
}
