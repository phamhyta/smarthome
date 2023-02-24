package com.iot.smarthome.repository;

import com.iot.smarthome.entity.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, Long> {

    List<HomeEntity> findByIdIn(List<Long> homeIds);

}
