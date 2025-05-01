package ru.fadeevaaa.staff.employeeservice.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fadeevaaa.staff.employeeservice.dto.UserDto;
import ru.fadeevaaa.staff.employeeservice.mapper.UserMapper;
import ru.fadeevaaa.staff.employeeservice.model.User;
import ru.fadeevaaa.staff.employeeservice.service.UserService;
import java.util.List;

@Validated
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService userService, @Qualifier("userMapperImpl") UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user,
                                           @RequestParam("companyId") @Min(1) long companyId) {
        log.info("Создание пользователя: {}", mapper.fromDto(user));
        User newUser = mapper.fromDto(user);
        newUser.setCompanyId(companyId);
        UserDto userDto = userService.create(newUser);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") @Min(1) long id) {
        log.info("Нахождение пользователя по ID {}", id);
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") @Min(1) long id,
                                                  @Valid @RequestBody UserDto updatedUserDto) {
        log.info("Обновление пользователя по ID {}, обновленные данные: {}", id, mapper.fromDto(updatedUserDto));
        UserDto userDto = userService.updateUser(id, mapper.fromDto(updatedUserDto));
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") @Min(1) long id) {
        log.info("Удаление пользователя по ID {}", id);
        return userService.deleteUser(id);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam("offset") @Min(0) Integer offset,
                                                     @RequestParam("limit") @Min(0) Integer limit) {
        log.info("Получение списка всех пользователей");
        Page<UserDto> userDto = userService.getAllUsers(offset, limit);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<UserDto>> getAllUsersByCompanyId(@PathVariable("companyId") @Min(1) long companyId) {
        log.info("Нахождение всех пользователей по ID компании {}", companyId);
        List<UserDto> usersDto = userService.getAllUsersByCompanyId(companyId);
        return ResponseEntity.ok(usersDto);
    }
}
