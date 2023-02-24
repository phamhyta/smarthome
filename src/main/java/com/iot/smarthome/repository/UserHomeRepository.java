package com.iot.smarthome.repository;

import com.iot.smarthome.entity.UserHomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHomeRepository extends JpaRepository<UserHomeEntity, Long> {
    List<UserHomeEntity> findByHomeId(Long homeId);

    List<UserHomeEntity> findByUserId(Long userId);
}
