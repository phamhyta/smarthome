package com.iot.smarthome.service;

import com.iot.smarthome.entity.SensorDataEntity;
import com.iot.smarthome.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public SensorDataEntity saveData(SensorDataEntity sensorDataEntity) {
        return sensorDataRepository.save(sensorDataEntity);
    }
}
