package ru.fadeevaaa.staff.employeeservice.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fadeevaaa.staff.employeeservice.dto.UserDto;
import ru.fadeevaaa.staff.employeeservice.model.User;

import java.util.List;

@Service
public interface UserService {

    UserDto create(User user);

    UserDto getUserById(long id);

    UserDto updateUser(long id, User updatedUser);

    ResponseEntity<UserDto> deleteUser(long id);

    Page<UserDto> getAllUsers(Integer offset, Integer limit);

    List<UserDto> getAllUsersByCompanyId(long companyId);
}
