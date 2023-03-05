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
        List<DeviceEntity> deviceEntities = deviceRepository.findByRoomId(roomId);

        return deviceEntities;
    }

    public DeviceEntity createNewDevice(DeviceEntity deviceEntity) {
        DeviceEntity createdDevice = deviceRepository.save(deviceEntity);
        mqttGateway.senToMqtt("0", "iot");
        return createdDevice;
    }

    public void toggleStatus(Long deviceId) {
        DeviceEntity deviceEntity = deviceRepository.findById(deviceId).get();
        deviceEntity.setStatus(!deviceEntity.getStatus());
        Boolean status = deviceEntity.getStatus();
        if (status == true) {
            mqttGateway.senToMqtt("1", "myIOT");
        } else {
            mqttGateway.senToMqtt("0", "myIOT");
        }
        deviceRepository.save(deviceEntity);
    }
}
