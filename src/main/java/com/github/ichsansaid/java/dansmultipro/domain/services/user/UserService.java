package com.github.ichsansaid.java.dansmultipro.domain.services.user;

import com.github.ichsansaid.java.dansmultipro.domain.dtos.errors.DataNotFoundException;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.CreateUserDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.UpdateUserPasswordDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.UpdateUserUsernameDto;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UserInvalidCredential;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UsernameAlreadyExistsException;
import com.github.ichsansaid.java.dansmultipro.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements  IUserService{
    private final IUserAggregation userAggregation;

    @Autowired
    public UserService(IUserAggregation userAggregation){
        this.userAggregation = userAggregation;
    }

    @Override
    public UserEntity createUser(CreateUserDto createDto) throws UsernameAlreadyExistsException, NoSuchAlgorithmException {
        UserEntity user = UserEntity.builder()
                .id(UUID.randomUUID())
                .username(createDto.getUsername())
                .plainPassword(createDto.getPassword())
        .build();
        return this.userAggregation.createEntity(user);
    }

    @Override
    public UserEntity updateUserUsername(
            UUID uid,
            UpdateUserUsernameDto updateDto
    ) throws DataNotFoundException, UsernameAlreadyExistsException, UserInvalidCredential {
        Optional<UserEntity> existsOptional = this.userAggregation.findUserById(uid);
        if (existsOptional.isEmpty()) {
            throw new DataNotFoundException("User tidak ditemukan");
        }
        UserEntity exists = existsOptional.get();
        if (exists.checkPassword(updateDto.getConfirmPassword())) {
            throw new UserInvalidCredential("Password tidak sesuai");
        }
        exists.setUsername(updateDto.getUsername());
        return this.userAggregation.updateEntity(exists);
    }

    @Override
    public UserEntity updateUserPassword(
            UUID uid,
            UpdateUserPasswordDto updateDto
    ) throws DataNotFoundException, UserInvalidCredential, NoSuchAlgorithmException, UsernameAlreadyExistsException {
        Optional<UserEntity> existsOptional = this.userAggregation.findUserById(uid);
        if (existsOptional.isEmpty()) {
            throw new DataNotFoundException("User tidak ditemukan");
        }
        UserEntity existsUser = existsOptional.get();
        if (existsUser.checkPassword(updateDto.oldPassword)) {
            throw new UserInvalidCredential("Password lama tidak sesuai");
        }
        existsUser.setPassword(updateDto.newPassword);
        return this.userAggregation.updateEntity(existsUser);
    }

    @Override
    public UserEntity getUserById(UUID uid) throws DataNotFoundException {
        Optional<UserEntity> existsOptional = this.userAggregation.findUserById(uid);
        if (existsOptional.isEmpty()) {
            throw new DataNotFoundException("User tidak ditemukan");
        }
        return existsOptional.get();
    }

    @Override
    public List<UserEntity> getUsers() {
        return this.userAggregation.findAllUser();
    }
}
