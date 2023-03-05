package com.iot.smarthome.controller;

import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.entity.HomeEntity;
import com.iot.smarthome.entity.RoomEntity;
import com.iot.smarthome.exception.UnknowException;
import com.iot.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/list/{homeId}")
    public ServerResponse getListRoomInHome(@PathVariable("homeId") Long homeId) {
        try {
            List<RoomEntity> roleEntities = roomService.getListRoomInHome(homeId);
            return ServerResponse.success("Danh sách phòng thành công!", roleEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknow exception list room in home!");
        }
    }

    @PostMapping("/")
    public ServerResponse createNewRoom(@RequestBody RoomEntity roomEntity) {
        try {
            RoomEntity createdRoom = roomService.createNewRoom(roomEntity);
            return ServerResponse.success("Tạo phòng thành công!", createdRoom);
        } catch (Exception exception) {
            throw new UnknowException("Unknow exception create new room!");
        }
    }

    @PutMapping("/")
    public ServerResponse updateRoom(@RequestBody RoomEntity roomEntity) {
        try {
            roomService.updateRoom(roomEntity);
            return ServerResponse.success("Cập nhật người dùng thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception update user!");
        }
    }

    @DeleteMapping("/{roomId}")
    public ServerResponse deleteRoom(@PathVariable Long roomId) {
        try {
            roomService.deleteRoom(roomId);
            return ServerResponse.success("Xoa nha thanh cong!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception delete home!");
        }
    }
}
