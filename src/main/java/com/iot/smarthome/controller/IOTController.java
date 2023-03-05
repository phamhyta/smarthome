package com.iot.smarthome.controller;

import com.iot.smarthome.config.MqttGateway;
import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.exception.UnknowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iot")
@CrossOrigin(origins = "*")
public class IOTController {

    @Autowired
    private MqttGateway mqttGateway;

    @PostMapping("/light/{status}")
    public ServerResponse toogleLight(@PathVariable("status") int status) {
        try {
            if(status == 0) mqttGateway.senToMqtt("0", "myIOT");
            else mqttGateway.senToMqtt("1", "myIOT");
            return ServerResponse.success("Tat bat den!");
        } catch (Exception exception) {
            throw new UnknowException("Unknowexception IOT controller!");
        }
    }
}
