package com.github.ichsansaid.java.dansmultipro.domain.services.user;

import com.github.ichsansaid.java.dansmultipro.domain.dtos.user_dto.LoginDto;
import com.github.ichsansaid.java.dansmultipro.domain.entities.UserEntity;
import com.github.ichsansaid.java.dansmultipro.domain.services.jwt.JwtService;
import com.github.ichsansaid.java.dansmultipro.infra.repositories.IUserRepository;
import com.github.ichsansaid.java.dansmultipro.infra.security.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserInfoDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = this.userRepository.findByUsername(username);
        return userEntity.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User tidak ditemukan"));
    }


}
