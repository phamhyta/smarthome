package com.iot.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorDataDTO {
    private Double humid;
    private Double temp;
    private String location;
    private Long deviceId;
}
