package ru.fadeevaaa.staff.employeeservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fadeevaaa.staff.employeeservice.dto.CompanyDto;
import ru.fadeevaaa.staff.employeeservice.dto.UserDto;
import ru.fadeevaaa.staff.employeeservice.mapper.UserMapper;
import ru.fadeevaaa.staff.employeeservice.model.User;
import ru.fadeevaaa.staff.employeeservice.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final APIClient apiClient;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, APIClient apiClient,
                           @Qualifier("userMapperImpl") UserMapper mapper) {
        this.userRepository = userRepository;
        this.apiClient = apiClient;
        this.mapper = mapper;
    }

    @Override
    public UserDto create(User user) {
        User newUser = userRepository.save(user);
        UserDto userDto = mapper.toDto(newUser);

        CompanyDto companyDto = apiClient.getUserCompanyById(user.getCompanyId());
        userDto.setCompany(companyDto);
        log.info("Пользователь {} успешно создан", newUser);
        return userDto;
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapper.toDto(user);

        CompanyDto companyDto = apiClient.getUserCompanyById(user.getCompanyId());
        userDto.setCompany(companyDto);
        log.info("По ID {} возвращен пользователь {}", userId, user);
        return userDto;
    }

    @Override
    public UserDto updateUser(long id, User updatedUser) {
        User user = userRepository.findById(id).get();
        updatedUser.setId(id);
        updatedUser.setCompanyId(user.getCompanyId());
        User updatedSavedUser = userRepository.save(updatedUser);
        UserDto userDto = mapper.toDto(updatedUser);

        CompanyDto companyDto = apiClient.getUserCompanyById(updatedUser.getCompanyId());
        userDto.setCompany(companyDto);
        log.info("Данные о пользователе с ID {} успешно изменены на {}", id, updatedSavedUser);
        return userDto;
    }

    @Override
    public ResponseEntity<UserDto> deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        log.info("Пользователь с ID {} успешно удален", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Page<UserDto> getAllUsers(Integer offset, Integer limit) {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = users.stream().map(user -> {
            UserDto userDto = mapper.toDto(user);
            CompanyDto companyDto = apiClient.getUserCompanyById(user.getCompanyId());
            userDto.setCompany(companyDto);
            return userDto;
        }).toList();

        Pageable pageable = PageRequest.of(offset, limit);
        long totalSize = usersDto.size();
        Page<UserDto> resultPage = new PageImpl<>(usersDto, pageable, totalSize);
        log.info("Возвращен список всех пользователей");
        return resultPage;
    }

    @Override
    public List<UserDto> getAllUsersByCompanyId(long companyId) {
        List<User> users = userRepository.findAllUsersByCompanyId(companyId);
        List<UserDto> usersDto = users.stream().map(mapper::toDto).collect(Collectors.toList());
        log.info("Возвращен список всех пользователей компании по ID {}", companyId);
        return usersDto;
    }
}
