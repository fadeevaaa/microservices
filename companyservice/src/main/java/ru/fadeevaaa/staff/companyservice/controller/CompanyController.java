package ru.fadeevaaa.staff.companyservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fadeevaaa.staff.companyservice.dto.CompanyDto;
import ru.fadeevaaa.staff.companyservice.dto.ResponseDto;
import ru.fadeevaaa.staff.companyservice.model.Company;
import ru.fadeevaaa.staff.companyservice.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addCompany(@RequestBody Company company) {
        ResponseDto responseDto = companyService.create(company);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCompanyById(@PathVariable("id") long id) {
        ResponseDto responseDto = companyService.getCompanyById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<CompanyDto> getUserCompanyById(@PathVariable("id") long id) {
        ResponseDto responseDto = companyService.getCompanyById(id);
        CompanyDto companyDto = responseDto.getCompany();
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCompany(@PathVariable("id") long id, @RequestBody Company updatedCompany) {
        ResponseDto responseDto = companyService.updateCompany(id, updatedCompany);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") long id) {
        return companyService.deleteCompany(id);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> getAllCompanies() {
        List<ResponseDto> responseDto = companyService.getAllCompanies();
        return ResponseEntity.ok(responseDto);
    }
}
