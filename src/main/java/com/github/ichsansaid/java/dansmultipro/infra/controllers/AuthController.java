package com.github.ichsansaid.java.dansmultipro.infra.controllers;

import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.LoginDto;
import com.github.ichsansaid.java.dansmultipro.domain.services.user.AuthService;
import com.github.ichsansaid.java.dansmultipro.domain.services.user.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthService authUserService;

    @PostMapping("/generate-token")
    public String generateToken(@Valid @RequestBody LoginDto login){
        System.out.println(login);
        return this.authUserService.generateToken(login);
    }
}
