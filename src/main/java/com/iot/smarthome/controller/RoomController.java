package com.iot.smarthome.controller;

import com.iot.smarthome.dto.ServerResponse;
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
}
