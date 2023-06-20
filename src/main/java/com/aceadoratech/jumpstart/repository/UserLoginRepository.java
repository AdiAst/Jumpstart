package com.aceadoratech.jumpstart.repository;

import com.aceadoratech.jumpstart.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<UserLogin,Integer> {
    Optional<UserLogin> findByEmail(String email);
}
