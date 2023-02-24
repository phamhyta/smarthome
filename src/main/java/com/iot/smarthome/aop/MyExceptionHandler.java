package com.iot.smarthome.aop;

import com.iot.smarthome.dto.ServerResponse;
import com.iot.smarthome.exception.UnknowException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(UnknowException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ServerResponse unknowException(UnknowException unknowException) {
        System.out.println("Controller advice, unknow exception!");
        return ServerResponse.error(HttpStatus.UNAUTHORIZED.value(), unknowException.getMessage());
    }
}
