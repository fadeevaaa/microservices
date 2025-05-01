package ru.fadeevaaa.staff.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fadeevaaa.staff.employeeservice.dto.CompanyDto;

@FeignClient(value = "companyservice", url = "http://companyservice:8086")
public interface APIClient {

    @GetMapping(value = "/companies/user/{id}")
    CompanyDto getUserCompanyById(@PathVariable("id") long companyId);
}
