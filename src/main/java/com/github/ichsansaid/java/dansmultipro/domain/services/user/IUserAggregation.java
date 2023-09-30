package com.github.ichsansaid.java.dansmultipro.domain.services.user;

import com.github.ichsansaid.java.dansmultipro.domain.dtos.errors.DataNotFoundException;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UserInvalidCredential;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UsernameAlreadyExistsException;
import com.github.ichsansaid.java.dansmultipro.domain.entities.UserEntity;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserAggregation {
    UserEntity createEntity(UserEntity entity) throws UsernameAlreadyExistsException, NoSuchAlgorithmException;
    UserEntity updateEntity(UserEntity entity) throws DataNotFoundException, UsernameAlreadyExistsException, UserInvalidCredential;

    Optional<UserEntity> findUserById(UUID uid);

    Optional<UserEntity> findUserByUsername(String username);

    List<UserEntity> findAllUser();

    void usernameExistsByUsername(String username) throws UsernameAlreadyExistsException;

    void usernameExistsByEntity(UserEntity entity) throws UsernameAlreadyExistsException;



}
