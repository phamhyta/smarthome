package com.iot.smarthome.service;

import com.iot.smarthome.config.MqttGateway;
import com.iot.smarthome.entity.DeviceEntity;
import com.iot.smarthome.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private MqttGateway mqttGateway;

    public List<DeviceEntity> getListDevice(Long roomId) {
        return deviceRepository.findByRoomId(roomId);
    }

    public DeviceEntity createNewDevice(DeviceEntity deviceEntity) {
        DeviceEntity createdDevice = deviceRepository.save(deviceEntity);
        mqttGateway.senToMqtt("Tat den", "myTopic");
        return createdDevice;
    }

    public void toggleStatus(Long deviceId) {
        DeviceEntity deviceEntity = deviceRepository.findById(deviceId).get();
        deviceEntity.setStatus(!deviceEntity.getStatus());
        Boolean status = deviceEntity.getStatus();
        if (status == true) {
            mqttGateway.senToMqtt("Bat den", "myTopic");
        } else {
            mqttGateway.senToMqtt("Tat den", "myTopic");
        }
        deviceRepository.save(deviceEntity);
    }
}