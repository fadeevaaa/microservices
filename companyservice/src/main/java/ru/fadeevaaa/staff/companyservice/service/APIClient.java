package ru.fadeevaaa.staff.companyservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fadeevaaa.staff.companyservice.dto.UserDto;

import java.util.List;

@FeignClient(value = "userservice", url = "http://userservice:8085")
public interface APIClient {

    @GetMapping(value = "/users/company/{companyId}")
    List<UserDto> getAllUsersByCompanyId(@PathVariable("companyId") long companyId);
}
