package com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDto {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
}
