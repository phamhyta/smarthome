package com.iot.smarthome.controller;

import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.entity.HomeEntity;
import com.iot.smarthome.entity.UserEntity;
import com.iot.smarthome.exception.UnknowException;
import com.iot.smarthome.service.HomeService;
import com.iot.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "*")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/list")
    public ServerResponse getListHomeForUser(@RequestParam Long userId) {
        try {
            List<HomeEntity> homeEntities = homeService.getListHomeForUser(userId);
            return ServerResponse.success("Lấy danh sách nhà thành công!", homeEntities);
        } catch (Exception exception) {
            throw new UnknowException("Unknown exception get list home for user!");
        }
    }

    @PostMapping("/")
    public ServerResponse createNewHome(@RequestBody HomeEntity homeEntity,
                                        @RequestParam Long userId) {
        try {
            HomeEntity createdHome = homeService.createNewHome(homeEntity, userId);
            return ServerResponse.success("Tạo nhà thành công!", createdHome);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception!");
        }
    }

    @PutMapping("/")
    public ServerResponse updateHome(@RequestBody HomeEntity homeEntity) {
        try {
            homeService.updateHome(homeEntity);
            return ServerResponse.success("Cập nhật người dùng thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception update user!");
        }
    }

    @DeleteMapping("/{homeId}")
    public ServerResponse deleteHome(@PathVariable Long homeId) {
        try {
            homeService.deleteHome(homeId);
            return ServerResponse.success("Xoa nha thanh cong!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception delete home!");
        }
    }
}
