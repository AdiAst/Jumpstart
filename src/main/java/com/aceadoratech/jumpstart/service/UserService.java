package com.aceadoratech.jumpstart.service;

import com.aceadoratech.jumpstart.entity.UserDetail;
import com.aceadoratech.jumpstart.entity.UserLogin;
import com.aceadoratech.jumpstart.repository.UserDetailRepository;
import com.aceadoratech.jumpstart.repository.UserLoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDetailRepository userDetailRepository;
    private final UserLoginRepository userLoginRepository;

    public UserLogin getSingleUser(String email){
        return userLoginRepository.findByEmail(email).get();
    }

    public UserDetail updateUser(UserDetail userDetail) {
        UserDetail currentUser = userDetailRepository.findById(userDetail.getId()).get();
        currentUser.setFirstname(userDetail.getFirstname());
        currentUser.setLastname(userDetail.getLastname());
        currentUser.setGender(userDetail.getGender());
        currentUser.setAge(userDetail.getAge());
        currentUser.setAddress(userDetail.getAddress());
        return userDetailRepository.save(currentUser);
    }
}
