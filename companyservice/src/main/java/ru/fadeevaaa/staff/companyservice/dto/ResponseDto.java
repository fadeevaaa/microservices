package ru.fadeevaaa.staff.companyservice.dto;

import java.util.List;

public class ResponseDto {

    private CompanyDto company;
    private List<UserDto> users;

    public ResponseDto(CompanyDto company, List<UserDto> users) {
        this.company = company;
        this.users = users;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public ResponseDto() {
    }
}
