package dev.basic.mysql.springbootrestapiwithmysql.service;

import dev.basic.mysql.springbootrestapiwithmysql.dto.UserDto;
import dev.basic.mysql.springbootrestapiwithmysql.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();

    void deleteUser(Long id);

    UserDto updateUser(UserDto user);
}
