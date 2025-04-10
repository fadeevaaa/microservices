package ru.fadeevaaa.staff.employeeservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fadeevaaa.staff.employeeservice.dto.ResponseDto;
import ru.fadeevaaa.staff.employeeservice.dto.UserDto;
import ru.fadeevaaa.staff.employeeservice.model.User;
import ru.fadeevaaa.staff.employeeservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addUser(@RequestBody User user) {
        ResponseDto responseDto = userService.create(user);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable("id") long id) {
        ResponseDto responseDto = userService.getUserById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable("id") long id, @RequestBody User updatedUser) {
        ResponseDto responseDto = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> getAllUsers() {
        List<ResponseDto> responseDto = userService.getAllUsers();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<UserDto>> getAllUsersByCompanyId(@PathVariable("companyId") long companyId) {
        List<UserDto> usersDto = userService.getAllUsersByCompanyId(companyId);
        return ResponseEntity.ok(usersDto);
    }
}
