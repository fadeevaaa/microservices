package ru.fadeevaaa.staff.companyservice.mapper;

import org.mapstruct.Mapper;
import ru.fadeevaaa.staff.companyservice.dto.CompanyDto;
import ru.fadeevaaa.staff.companyservice.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto toDto(Company company);
    Company fromDto(CompanyDto dto);
}
