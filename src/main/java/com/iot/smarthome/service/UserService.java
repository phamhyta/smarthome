package com.iot.smarthome.service;

import com.iot.smarthome.entity.UserEntity;
import com.iot.smarthome.entity.UserHomeEntity;
import com.iot.smarthome.repository.UserHomeRepository;
import com.iot.smarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHomeRepository userHomeRepository;


    public List<UserEntity> getListUserInHome(Long homeId) {
        List<UserHomeEntity> userHomeEntity = userHomeRepository.findByHomeId(homeId);
        List<Long> userIds = userHomeEntity.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        List<UserEntity> userEntities = userRepository.findByIdIn(userIds);
        return userEntities;
    }
}
