package com.iot.smarthome.service;

import com.iot.smarthome.dto.AnalyticDTO;
import com.iot.smarthome.entity.SensorDataEntity;
import com.iot.smarthome.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public AnalyticDTO getAnalyticData() {
        AnalyticDTO analyticDTO = new AnalyticDTO();
        SensorDataEntity sensorDataEntity = sensorDataRepository.getLatest();
        analyticDTO.setCurrentTemp(sensorDataEntity.getTemp());
        analyticDTO.setCurrentHumid(sensorDataEntity.getHumid());
        List<SensorDataEntity> sensorDataEntities = sensorDataRepository.get10Latest();
        analyticDTO.setSensorData(sensorDataEntities);
        return analyticDTO;
    }
}
