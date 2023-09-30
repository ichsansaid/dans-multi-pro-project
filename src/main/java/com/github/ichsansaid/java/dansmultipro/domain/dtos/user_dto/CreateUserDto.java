package com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter @Setter @AllArgsConstructor(staticName = "build")
public class CreateUserDto {

    @NotBlank(message = "Username tidak boleh kosong")
    public String username;

    @NotBlank(message = "Password tidak boleh kosong")
    public String password;
}
