package dev.basic.mysql.springbootrestapiwithmysql.service.impl;

import dev.basic.mysql.springbootrestapiwithmysql.dto.UserDto;
import dev.basic.mysql.springbootrestapiwithmysql.entity.User;
import dev.basic.mysql.springbootrestapiwithmysql.exception.EmailAlreadyExistException;
import dev.basic.mysql.springbootrestapiwithmysql.exception.UserNotFoundException;
import dev.basic.mysql.springbootrestapiwithmysql.mapper.UserMapper;
import dev.basic.mysql.springbootrestapiwithmysql.mapper.UserMapperWithMapstruct;
import dev.basic.mysql.springbootrestapiwithmysql.repository.UserRepository;
import dev.basic.mysql.springbootrestapiwithmysql.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // User user = UserMapper.mapToUser(userDto);
        // User user = modelMapper.map(userDto, User.class);
        Optional<User> optional = userRepository.findByEmail(userDto.getEmail());
        if (optional.isPresent()) {
            throw new EmailAlreadyExistException("Email already exists " + userDto.getEmail());
        }
        User user = UserMapperWithMapstruct.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = UserMapperWithMapstruct.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        //UserDto userDto = UserMapper.mapToUserDto( user.orElseThrow(RuntimeException::new));
        // UserDto userDto = modelMapper.map(user.orElseThrow(RuntimeException::new), UserDto.class);
        UserDto userDto = UserMapperWithMapstruct.MAPPER.mapToUserDto(user.orElseThrow(
                () -> new UserNotFoundException("User", "id", id)));
        return userDto;

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> UserMapperWithMapstruct.MAPPER.mapToUserDto(user))
                //.map(user -> modelMapper.map(user, UserDto.class))
                //.map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        User userFromDB = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User", "id", id));
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User userFromDB = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new UserNotFoundException("User", "id", userDto.getId()));
        userFromDB.setFirstName(userDto.getFirstName());
        userFromDB.setLastName(userDto.getLastName());
        userFromDB.setEmail(userDto.getEmail());
        User savedUser = userRepository.save(userFromDB);
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = UserMapperWithMapstruct.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }
}
