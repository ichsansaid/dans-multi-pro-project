package com.github.ichsansaid.java.dansmultipro.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "public")
@Getter()
@Setter()
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String plainPassword;

    public boolean checkPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return !bCryptPasswordEncoder.matches(password, this.password);
    }

    public void hashPassword() throws NoSuchAlgorithmException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(this.plainPassword);
    }
}
