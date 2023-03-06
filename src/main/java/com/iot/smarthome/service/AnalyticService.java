package com.iot.smarthome.service;

import com.iot.smarthome.dto.AnalyticDTO;
import com.iot.smarthome.dto.AverageDTO;
import com.iot.smarthome.entity.SensorDataEntity;
import com.iot.smarthome.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public AnalyticDTO getCurrentData() {
        AnalyticDTO analyticDTO = new AnalyticDTO();
        SensorDataEntity sensorDataEntity = sensorDataRepository.getLatest();
        analyticDTO.setCurrentTemp(sensorDataEntity.getTemp());
        analyticDTO.setCurrentHumid(sensorDataEntity.getHumid());
        return analyticDTO;
    }

    public AnalyticDTO getAverage() {
        AnalyticDTO analyticDTO = new AnalyticDTO();
        List<Object[]> objectList = sensorDataRepository.getAverage();
        List<AverageDTO> averageDTOS = objectList.stream()
                .map(objects -> {
                    AverageDTO averageDTO = new AverageDTO();
                    averageDTO.setTemp(objects[3]);
                    averageDTO.setHumid(objects[4]);
                    averageDTO.setTime(objects[0]);
                    return averageDTO;
                })
                .collect(Collectors.toList());
        analyticDTO.setAverageData(averageDTOS);
        return analyticDTO;
    }
}
