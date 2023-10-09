package dev.basic.mysql.springbootrestapiwithmysql.controller;

import dev.basic.mysql.springbootrestapiwithmysql.dto.UserDto;
import dev.basic.mysql.springbootrestapiwithmysql.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User")
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    @Operation(summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database")
    @ApiResponse(responseCode = "201",
            description = "HTTP Status 201 Created")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @Operation(summary = "Get User By id REST API",
            description = "Get User By id REST API is used to get user from a database")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status 200 SUCCESS")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get all users from a database")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status 200 SUCCESS")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> listUsers = userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @Operation(summary = "Update User REST API",
            description = "Update User REST API is used to update user in a database")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status 200 SUCCESS")
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDto userDto) {
        userDto.setId(id);
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "Delete User REST API",
            description = "Delete User REST API is used to delete user from a database")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status 200 SUCCESS")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User was deleted", HttpStatus.OK);
    }
}
