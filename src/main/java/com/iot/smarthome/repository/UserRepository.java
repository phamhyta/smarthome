package com.iot.smarthome.repository;

import com.iot.smarthome.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPassword(String username, String password);

    List<UserEntity> findByIdIn(List<Long> userIds);
}
