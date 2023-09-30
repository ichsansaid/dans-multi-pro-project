package com.github.ichsansaid.java.dansmultipro.infra.controllers;

import com.github.ichsansaid.java.dansmultipro.domain.dtos.errors.DataNotFoundException;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.CreateUserDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.UpdateUserPasswordDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.UpdateUserUsernameDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UserInvalidCredential;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UsernameAlreadyExistsException;
import com.github.ichsansaid.java.dansmultipro.domain.entities.UserEntity;
import com.github.ichsansaid.java.dansmultipro.domain.services.user.IUserService;
import com.github.ichsansaid.java.dansmultipro.infra.dtos.UserSafeDto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    public final IUserService userService;
    public final ModelMapper modelMapper;

    @Autowired
    public UserController(IUserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<UserSafeDto> getUsers(){
        List<UserEntity> userEntities = this.userService.getUsers();
        return userEntities.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserSafeDto.class))
                .toList();
    }

    @PostMapping("")
    public UserSafeDto createUsers(@Valid @RequestBody CreateUserDto user) throws DataNotFoundException, UsernameAlreadyExistsException, NoSuchAlgorithmException {
        return this.modelMapper.map(this.userService.createUser(user), UserSafeDto.class);
    }

    @PatchMapping(value = "/{userId}/username")
    public UserSafeDto updateUserUsername(@PathVariable String userId, @Valid @RequestBody UpdateUserUsernameDto user) throws DataNotFoundException, UsernameAlreadyExistsException, UserInvalidCredential, NoSuchAlgorithmException {
        return this.modelMapper.map(this.userService.updateUserUsername(UUID.fromString(userId), user), UserSafeDto.class);
    }

    @PatchMapping(value = "/{userId}/password")
    public UserSafeDto updateUserPassword(@PathVariable String userId, @Valid @RequestBody UpdateUserPasswordDto user) throws DataNotFoundException, UsernameAlreadyExistsException, UserInvalidCredential, NoSuchAlgorithmException {
        return this.modelMapper.map(this.userService.updateUserPassword(UUID.fromString(userId), user), UserSafeDto.class);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public String handleNotFound(DataNotFoundException exception){
        return exception.getMessage();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ UsernameAlreadyExistsException.class , UserInvalidCredential.class})
    public String handleBadRequestStandard(Exception exception){
        return exception.getMessage();
    }
}
