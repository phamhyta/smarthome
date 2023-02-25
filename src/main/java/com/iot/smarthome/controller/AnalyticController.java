package com.iot.smarthome.controller;

import com.iot.smarthome.dto.AnalyticDTO;
import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.exception.UnknowException;
import com.iot.smarthome.service.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytic")
public class AnalyticController {

    @Autowired
    private AnalyticService analyticService;

    @GetMapping("/")
    public ServerResponse getAnalyticData() {
        try {
            AnalyticDTO analyticDTO = analyticService.getAnalyticData();
            return ServerResponse.success("Lấy data thành công!", analyticDTO);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get analytic data");
        }
    }

}
