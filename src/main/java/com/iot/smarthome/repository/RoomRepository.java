package com.iot.smarthome.repository;

import com.iot.smarthome.entity.RoleEntity;
import com.iot.smarthome.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    List<RoomEntity> findByHomeId(Long homeId);
}
