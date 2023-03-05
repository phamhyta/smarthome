package com.iot.smarthome.service;

import com.iot.smarthome.entity.RoomEntity;
import com.iot.smarthome.entity.UserEntity;
import com.iot.smarthome.repository.DeviceRepository;
import com.iot.smarthome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<RoomEntity> getListRoomInHome(Long homeId) {
        return roomRepository.findByHomeId(homeId);
    }

    public RoomEntity createNewRoom(RoomEntity roomEntity) {
        return roomRepository.save(roomEntity);
    }

    public void updateRoom(RoomEntity roomEntity) {
        RoomEntity oldRoom = roomRepository.findById(roomEntity.getId()).get();
        oldRoom.setName(roomEntity.getName());
        roomRepository.save(oldRoom);
    }

    public void deleteRoom(Long roomId) {
        List<Long> deviceIds = deviceRepository.findByRoomId(roomId)
                .stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        deviceRepository.deleteAllById(deviceIds);
        roomRepository.deleteById(roomId);
    }
}
