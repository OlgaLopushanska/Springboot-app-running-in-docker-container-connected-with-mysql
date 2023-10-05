package dev.basic.mysql.springbootrestapiwithmysql.mapper;

import dev.basic.mysql.springbootrestapiwithmysql.dto.UserDto;
import dev.basic.mysql.springbootrestapiwithmysql.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(user.getId(),
                user.getFirstName(), user.getLastName(), user.getEmail());
        return userDto;
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getFirstName(),
                userDto.getLastName(), userDto.getEmail());
        return user;
    }
}
