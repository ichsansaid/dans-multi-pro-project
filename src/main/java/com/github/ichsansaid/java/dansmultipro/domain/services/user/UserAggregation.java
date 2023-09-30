package com.github.ichsansaid.java.dansmultipro.domain.services.user;

import com.github.ichsansaid.java.dansmultipro.domain.dtos.errors.DataNotFoundException;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UserInvalidCredential;
import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.errors.UsernameAlreadyExistsException;
import com.github.ichsansaid.java.dansmultipro.domain.entities.UserEntity;
import com.github.ichsansaid.java.dansmultipro.infra.repositories.IUserRepository;
import com.github.ichsansaid.java.dansmultipro.infra.security.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAggregation implements IUserAggregation {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createEntity(UserEntity entity) throws UsernameAlreadyExistsException, NoSuchAlgorithmException {
        entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
        this.usernameExistsByUsername(entity.getUsername());
        return this.userRepository.save(entity);
    }

    @Override
    public UserEntity updateEntity(UserEntity entity) throws DataNotFoundException, UsernameAlreadyExistsException, UserInvalidCredential {
        this.usernameExistsByEntity(entity);
        return this.userRepository.save(entity);
    }


    @Override
    public Optional<UserEntity> findUserById(UUID uid) {
        return this.userRepository.findById(uid);
    }

    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<UserEntity> findAllUser() {
        return (List<UserEntity>) this.userRepository.findAll();
    }

    @Override
    public void usernameExistsByUsername(String username) throws UsernameAlreadyExistsException {
        Optional<UserEntity> existUserUsername = this.userRepository.findByUsername(username);
        if (existUserUsername.isPresent()){
            throw new UsernameAlreadyExistsException("Username telah tersedia");
        }
    }

    @Override
    public void usernameExistsByEntity(UserEntity entity) throws UsernameAlreadyExistsException {
        Optional<UserEntity> existUserUsername = this.userRepository.findByUsername(entity.getUsername());
        if (existUserUsername.isPresent() && !existUserUsername.get().getId().equals(entity.getId())) {
            throw new UsernameAlreadyExistsException("Username telah tersedia");
        }
    }
}
