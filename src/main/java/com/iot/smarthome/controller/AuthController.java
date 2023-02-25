package com.iot.smarthome.controller;

import com.iot.smarthome.entity.UserEntity;
import com.iot.smarthome.exception.UnknowException;
import com.iot.smarthome.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public UserEntity signIn(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = authService.signIn(userEntity);
            if(user == null) user.getUsername();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknowexception sign in!");
        }
    }

    @PostMapping("/signup")
    public UserEntity signup(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = authService.signup(userEntity);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknowexception sign in!");
        }
    }
}
