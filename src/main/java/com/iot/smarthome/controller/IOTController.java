package com.iot.smarthome.controller;

import com.iot.smarthome.config.MqttGateway;
import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.exception.UnknowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/iot")
public class IOTController {

    @Autowired
    private MqttGateway mqttGateway;

    @PostMapping("/light/{status}")
    public ServerResponse toogleLight(@PathVariable("status") int status) {
        try {
            if(status == 0) mqttGateway.senToMqtt("Tat den", "myTopic");
            else mqttGateway.senToMqtt("Bat den", "myTopic");
            return ServerResponse.success("Tat bat den!");
        } catch (Exception exception) {
            throw new UnknowException("Unknowexception IOT controller!");
        }
    }
}
