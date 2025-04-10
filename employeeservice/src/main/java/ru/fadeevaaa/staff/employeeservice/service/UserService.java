package ru.fadeevaaa.staff.employeeservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fadeevaaa.staff.employeeservice.dto.ResponseDto;
import ru.fadeevaaa.staff.employeeservice.dto.UserDto;
import ru.fadeevaaa.staff.employeeservice.model.User;

import java.util.List;

@Service
public interface UserService {

    ResponseDto create(User user);

    ResponseDto getUserById(long id);

    ResponseDto updateUser(long id, User updatedUser);

    ResponseEntity<User> deleteUser(long id);

    List<ResponseDto> getAllUsers();

    List<UserDto> getAllUsersByCompanyId(long companyId);
}
