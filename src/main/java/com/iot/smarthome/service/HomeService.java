package com.iot.smarthome.service;

import com.iot.smarthome.entity.HomeEntity;
import com.iot.smarthome.entity.UserHomeEntity;
import com.iot.smarthome.repository.HomeRepository;
import com.iot.smarthome.repository.RoomRepository;
import com.iot.smarthome.repository.UserHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private UserHomeRepository userHomeRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;


    public List<HomeEntity> getListHomeForUser(Long userId) {
        List<Long> homeIds = userHomeRepository.findByUserId(userId)
                .stream()
                .map(e -> e.getHomeId())
                .collect(Collectors.toList());
        return homeRepository.findByIdIn(homeIds);
    }

    public HomeEntity createNewHome(HomeEntity homeEntity, Long userId) {
        HomeEntity createdHome = homeRepository.save(homeEntity);
        UserHomeEntity userHome = new UserHomeEntity();
        userHome.setHomeId(createdHome.getId());
        userHome.setUserId(userId);
        userHomeRepository.save(userHome);
        return createdHome;
    }

    public void updateHome(HomeEntity homeEntity) {
        HomeEntity oldHome = homeRepository.findById(homeEntity.getId()).get();
        oldHome.setName(homeEntity.getName());
        oldHome.setLocation(homeEntity.getLocation());
        homeRepository.save(oldHome);
    }

    public void deleteHome(Long homeId) {
        roomRepository.findByHomeId(homeId)
                .stream()
                .map(e->e.getId())
                .forEach(e -> roomService.deleteRoom(e));

        List<Long> userHomeIds = userHomeRepository.findByHomeId(homeId)
                .stream().map(e->e.getId()).collect(Collectors.toList());
        userHomeRepository.deleteAllById(userHomeIds);


        homeRepository.deleteById(homeId);
    }
}
