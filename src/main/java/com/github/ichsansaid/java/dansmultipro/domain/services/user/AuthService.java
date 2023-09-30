package com.github.ichsansaid.java.dansmultipro.domain.services.user;

import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.LoginDto;
import com.github.ichsansaid.java.dansmultipro.domain.entities.UserEntity;
import com.github.ichsansaid.java.dansmultipro.domain.services.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public String generateToken(LoginDto login) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    login.getUsername(), login.getPassword()
                            )
                    );
            String username = authenticate.getName();
            return this.jwtService.generateToken(username);
        }catch (Exception ex) {
            System.out.println(ex.getClass());
        }
        return "";
    }
}
