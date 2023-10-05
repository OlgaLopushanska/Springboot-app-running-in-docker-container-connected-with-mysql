package dev.basic.mysql.springbootrestapiwithmysql.mapper;

import dev.basic.mysql.springbootrestapiwithmysql.dto.UserDto;
import dev.basic.mysql.springbootrestapiwithmysql.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapperWithMapstruct {
    UserMapperWithMapstruct MAPPER = Mappers.getMapper(UserMapperWithMapstruct.class);
    //@Mapping(source = "email", target = "emailAddress")
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
