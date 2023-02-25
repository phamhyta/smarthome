package com.iot.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SensorDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private Double temp;
    private Double humid;

    private LocalDateTime time;

    @Column(name = "device_id")
    private Long deviceId;
}
