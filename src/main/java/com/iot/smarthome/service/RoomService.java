package com.iot.smarthome.service;

import com.iot.smarthome.entity.RoomEntity;
import com.iot.smarthome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<RoomEntity> getListRoomInHome(Long homeId) {
        return roomRepository.findByHomeId(homeId);
    }

    public RoomEntity createNewRoom(RoomEntity roomEntity) {
        return roomRepository.save(roomEntity);
    }
}
