package com.iot.smarthome.dto;

import com.iot.smarthome.entity.SensorDataEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnalyticDTO {

    private Double currentTemp;
    private Double currentHumid;
    private List<SensorDataEntity> sensorData;
}
