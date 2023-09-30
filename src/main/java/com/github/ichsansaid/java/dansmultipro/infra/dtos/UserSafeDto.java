package com.github.ichsansaid.java.dansmultipro.infra.dtos;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserSafeDto {
    private UUID id;
    private String username;
    private String password;

    public String getPassword(){
        return "****";
    }
}
