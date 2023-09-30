package com.github.ichsansaid.java.dansmultipro.domain.services.user;

import com.github.ichsansaid.java.dansmultipro.domain.dtos.errors.DataNotFoundException;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.CreateUserDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.FilterUserDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.UpdateUserPasswordDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.UpdateUserUsernameDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UserInvalidCredential;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UsernameAlreadyExistsException;
import com.github.ichsansaid.java.dansmultipro.domain.entities.UserEntity;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserEntity createUser(CreateUserDto createDto) throws DataNotFoundException, UsernameAlreadyExistsException, NoSuchAlgorithmException;
    UserEntity updateUserUsername(UUID uid, UpdateUserUsernameDto updateDto) throws DataNotFoundException, UsernameAlreadyExistsException, NoSuchAlgorithmException, UserInvalidCredential;
    UserEntity updateUserPassword(UUID uid, UpdateUserPasswordDto updateDto) throws DataNotFoundException, UserInvalidCredential, NoSuchAlgorithmException, UsernameAlreadyExistsException;
    UserEntity getUserById(UUID uuid) throws DataNotFoundException;

    List<UserEntity> getUsers();

}
