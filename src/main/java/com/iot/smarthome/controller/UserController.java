package com.iot.smarthome.controller;

import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.entity.UserEntity;
import com.iot.smarthome.exception.UnknowException;
import com.iot.smarthome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3001")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/list/{homeId}")
    public ServerResponse getListUserHome(@PathVariable("homeId") Long homeId) {
        try {
            List<UserEntity> userEntities = userService.getListUserInHome(homeId);
            return ServerResponse.success("Danh sách người dùng", userEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknow exception api user");
        }
    }
}
