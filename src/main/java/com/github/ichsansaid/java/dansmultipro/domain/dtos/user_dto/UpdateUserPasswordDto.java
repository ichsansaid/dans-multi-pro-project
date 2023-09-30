package com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserPasswordDto {
    @NotBlank(message = "Password lama tidak boleh kosong")
    public String oldPassword;

    @NotBlank(message = "Password baru tidak boleh kosong")
    public String newPassword;
}
