package com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FilterUserDto {
    public UUID id;
    public String username;
}
