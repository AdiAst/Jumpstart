package com.aceadoratech.jumpstart.service;

import com.aceadoratech.jumpstart.entity.UserLogin;
import com.aceadoratech.jumpstart.repository.UserLoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserLoginRepository userLoginRepository;

    public UserLogin getSingleUser(String email){
        return userLoginRepository.findByEmail(email).get();
    }
}
