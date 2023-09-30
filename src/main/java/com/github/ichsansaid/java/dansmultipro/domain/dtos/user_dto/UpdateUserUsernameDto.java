package com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateUserUsernameDto {
    @NotBlank(message = "Username tidak boleh kosong")
    public String username;

    @NotBlank(message = "Konfirmasi password tidak boleh kosong")
    public String confirmPassword;
}
