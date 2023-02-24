package com.iot.smarthome.service;

import com.iot.smarthome.entity.UserEntity;
import com.iot.smarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;


    public UserEntity signIn(UserEntity userEntity) {
        UserEntity user = userRepository.findByUsernameAndPassword(userEntity.getUsername(), userEntity.getPassword());
        return user;
    }

    public UserEntity signup(UserEntity userEntity) {
        UserEntity createdUser = userRepository.save(userEntity);
        return createdUser;
    }
}
