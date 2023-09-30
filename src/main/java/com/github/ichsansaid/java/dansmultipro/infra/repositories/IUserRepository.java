package com.github.ichsansaid.java.dansmultipro.infra.repositories;

import com.github.ichsansaid.java.dansmultipro.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends CrudRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}
