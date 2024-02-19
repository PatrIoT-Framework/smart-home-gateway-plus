package io.patriotframework.smarthomeplusgateway.API.handlers;

import io.patriotframework.smarthomeplusgateway.DTOs.DeviceDTO;
import io.patriotframework.smarthomeplusgateway.DTOs.DevicesDTO;
import io.patriotframework.smarthomeplusgateway.FindStatus;
import io.patriotframework.smarthomeplusgateway.GateWay;
import org.springframework.beans.factory.annotation.Autowired;

public class GateWayHandler {

    private final GateWay gateWay;

    @Autowired
    public GateWayHandler(GateWay gateWay) {
        this.gateWay= gateWay;
    }

    public DevicesDTO handleFindGetRequest() {
        return gateWay.findDevices();
    }

    public DevicesDTO handleGetRequest() {
        return gateWay.getDevices();
    }

}
