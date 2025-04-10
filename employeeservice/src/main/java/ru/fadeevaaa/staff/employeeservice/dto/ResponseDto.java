package ru.fadeevaaa.staff.employeeservice.dto;

public class ResponseDto {

    private CompanyDto company;
    private UserDto user;

    public ResponseDto(CompanyDto company, UserDto user) {
        this.company = company;
        this.user = user;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ResponseDto() {
    }
}
