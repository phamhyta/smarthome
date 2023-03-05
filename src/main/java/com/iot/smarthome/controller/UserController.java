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
@CrossOrigin(origins = "*")
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

    @PostMapping("/")
    public ServerResponse createUserInHome(@RequestParam Long homeId,
                                           @RequestBody UserEntity userEntity) {
        try {
            UserEntity createdUser = userService.createUserInHome(homeId, userEntity);
            return ServerResponse.success("Thêm người thành công!", createdUser);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception create user in home!");
        }
    }

    @PutMapping("/")
    public ServerResponse updateUser(@RequestBody UserEntity userEntity) {
        try {
            userService.updateUser(userEntity);
            return ServerResponse.success("Cập nhật người dùng thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception update user!");
        }
    }

    @DeleteMapping("/{userId}")
    public ServerResponse deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ServerResponse.success("Xoa nguoi dung thanh cong!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception delete user!");
        }
    }
}
