package com.iot.smarthome.repository;

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
}
