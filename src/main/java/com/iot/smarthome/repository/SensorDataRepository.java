package com.iot.smarthome.repository;

import com.iot.smarthome.dto.AverageDTO;
import com.iot.smarthome.entity.SensorDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorDataEntity, Long> {

    @Query(value = "SELECT * FROM sensor_data " +
            "ORDER BY id DESC LIMIT 1", nativeQuery = true)
    SensorDataEntity getLatest();

    @Query(value = "SELECT * FROM sensor_data " +
            "ORDER BY id DESC limit 10", nativeQuery = true)
    List<SensorDataEntity> get10Latest();

    @Query(value = "SELECT DATE(time) AS date, HOUR(time) AS hour, MINUTE(time) AS minute, AVG(temp) AS temp, AVG(humid) AS humid " +
        "FROM sensor_data " +
        "GROUP BY DATE(time), HOUR(time), MINUTE(time) " +
        "ORDER BY date DESC, hour DESC, minute DESC " +
        "LIMIT 30", nativeQuery = true)
    List<Object[]> getAverage();
}
