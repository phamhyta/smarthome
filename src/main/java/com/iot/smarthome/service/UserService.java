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

    public UserEntity createUserInHome(Long homeId, UserEntity userEntity) {
        UserEntity createdUser = userRepository.save(userEntity);
        UserHomeEntity userHomeEntity = new UserHomeEntity();
        userHomeEntity.setUserId(createdUser.getId());
        userHomeEntity.setHomeId(homeId);
        userHomeRepository.save(userHomeEntity);
        return createdUser;
    }

    public void updateUser(UserEntity userEntity) {
        UserEntity oldUser = userRepository.findById(userEntity.getId()).get();
        oldUser.setFullname(userEntity.getFullname());
        userRepository.save(oldUser);
    }

    public void deleteUser(Long userId) {
        List<Long> userHomeIds = userHomeRepository.findByUserId(userId)
                        .stream().map(e->e.getId()).collect(Collectors.toList());
        userHomeRepository.deleteAllById(userHomeIds);
        userRepository.deleteById(userId);
    }
}
