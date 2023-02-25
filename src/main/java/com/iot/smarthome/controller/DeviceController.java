package com.iot.smarthome.controller;

import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.entity.DeviceEntity;
import com.iot.smarthome.exception.UnknowException;
import com.iot.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/list")
    public ServerResponse getListDevice(@RequestParam Long roomId) {
        try {
            List<DeviceEntity> deviceEntities = deviceService.getListDevice(roomId);
            return ServerResponse.success("Danh sach thiet bi thanh cong", deviceEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get list device!");
        }
    }

    @GetMapping("/")
    public ServerResponse createNewDevice(@RequestBody DeviceEntity deviceEntity) {
        try {
            DeviceEntity createdDevice = deviceService.createNewDevice(deviceEntity);
            return ServerResponse.success("Tao moi device thanh cong!", createdDevice);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknow exception create new device!");
        }
    }

    @PutMapping("/")
    public ServerResponse toggleStatus(@RequestParam Long deviceId) {
        try {
            deviceService.toggleStatus(deviceId);
            return ServerResponse.success("Cập nhật thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception toggle status!");
        }
    }
}
