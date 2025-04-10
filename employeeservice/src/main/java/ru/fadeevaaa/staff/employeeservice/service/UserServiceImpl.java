package ru.fadeevaaa.staff.employeeservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fadeevaaa.staff.employeeservice.dto.CompanyDto;
import ru.fadeevaaa.staff.employeeservice.dto.ResponseDto;
import ru.fadeevaaa.staff.employeeservice.dto.UserDto;
import ru.fadeevaaa.staff.employeeservice.model.User;
import ru.fadeevaaa.staff.employeeservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final APIClient apiClient;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, APIClient apiClient) {
        this.userRepository = userRepository;
        this.apiClient = apiClient;
    }

    @Override
    public ResponseDto create(User user) {
        ResponseDto responseDto = new ResponseDto();
        User newUser = userRepository.save(user);
        UserDto userDto = mapToUser(newUser);

        CompanyDto companyDto = apiClient.getUserCompanyById(user.getCompanyId());
        responseDto.setUser(userDto);
        responseDto.setCompany(companyDto);

        return responseDto;
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

//    @Override
//    public ResponseEntity<User> getUserById(long id) {
//        Optional<User> user = userRepository.findById(id);
//        if (user.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(user.get(), HttpStatus.OK);
//    }

    @Override
    public ResponseDto getUserById(long userId) {

        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        CompanyDto companyDto = apiClient.getUserCompanyById(user.getCompanyId());
        responseDto.setUser(userDto);
        responseDto.setCompany(companyDto);

        return responseDto;
    }

    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

    @Override
    public ResponseDto updateUser(long id, User updatedUser) {
        updatedUser.setId(id);
        User updatedSavedUser = userRepository.save(updatedUser);
        ResponseDto responseDto = new ResponseDto();
        UserDto userDto = mapToUser(updatedUser);

        CompanyDto companyDto = apiClient.getUserCompanyById(updatedUser.getCompanyId());
        responseDto.setUser(userDto);
        responseDto.setCompany(companyDto);

        return responseDto;
    }

    @Override
    public ResponseEntity<User> deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<ResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<ResponseDto> responsesDto = users.stream().map(user -> {
            ResponseDto responseDto = new ResponseDto();
            UserDto userDto = mapToUser(user);
            CompanyDto companyDto = apiClient.getUserCompanyById(user.getCompanyId());
            responseDto.setUser(userDto);
            responseDto.setCompany(companyDto);
            return responseDto;
        }).toList();

        return responsesDto;
//        ResponseDto responseDto = new ResponseDto();
//        UserDto userDto = mapToUser(user);
//
//        CompanyDto companyDto = apiClient.getCompanyById(user.getCompanyId());
//        responseDto.setUser(userDto);
//        responseDto.setCompany(companyDto);
//
//        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public List<UserDto> getAllUsersByCompanyId(long companyId) {
        List<User> users = userRepository.findAllUsersByCompanyId(companyId);
        List<UserDto> usersDto = users.stream().map(this::mapToUser).collect(Collectors.toList());
        return usersDto;
    }
}
