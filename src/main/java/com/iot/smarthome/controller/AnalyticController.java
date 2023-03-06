package com.iot.smarthome.controller;

import com.iot.smarthome.dto.AnalyticDTO;
import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.exception.UnknowException;
import com.iot.smarthome.service.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytic")
@CrossOrigin(origins = "*")
public class AnalyticController {

    @Autowired
    private AnalyticService analyticService;

    @GetMapping("/current")
    public ServerResponse getCurrent() {
        try {
            AnalyticDTO analyticDTO = analyticService.getCurrentData();
            return ServerResponse.success("Lấy current data thành công!", analyticDTO);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get current!");
        }
    }

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

    @GetMapping("/average")
    public ServerResponse getAverage() {
        try {
            AnalyticDTO analyticDTO = analyticService.getAverage();
            return ServerResponse.success("Lấy data trung bình thành công!", analyticDTO);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get average data!");
        }
    }

}
