package io.patriotframework.smarthomeplusgateway.API.controllers.devices;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/devices/thermometer")
public class ThermometerController {

    @PostMapping
    public void addThermometer() {


    }

}
