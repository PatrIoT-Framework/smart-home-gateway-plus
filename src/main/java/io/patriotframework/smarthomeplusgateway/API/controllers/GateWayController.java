package io.patriotframework.smarthomeplusgateway.API.controllers;

import io.patriotframework.smarthomeplusgateway.API.APIRoutes;
import io.patriotframework.smarthomeplusgateway.API.handlers.GateWayHandler;
import io.patriotframework.smarthomeplusgateway.APIVersions;
import io.patriotframework.smarthomeplusgateway.DTOs.DevicesDTO;
import io.patriotframework.smarthomeplusgateway.DTOs.TemperatureDTO;
import io.patriotframework.smarthomeplusgateway.GateWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GateWayController extends GateWayHandler {

    @Autowired
    public GateWayController(GateWay gateWay) {
        super(gateWay);
    }



    /**
     * Returns available devices
     *
     * @param apiVersion api version specified in route
     * @return new devices
     */
    @GetMapping(APIRoutes.FIND_NEW_DEVICES)
    public DevicesDTO findNewDevices(@PathVariable String apiVersion) {
        if(apiVersion.equals(APIVersions.V0_1)) {
            return handleFindGetRequest();
        }
        return  null;
    }

    /**
     * Returns registered devices
     *
     * @param apiVersion api version specified in route
     * @return registered devices
     */
    @GetMapping(APIRoutes.GATEWAY_ROUTE)
    public DevicesDTO getDevices(@PathVariable String apiVersion) {
        if(apiVersion.equals(APIVersions.V0_1)) {
            return handleGetRequest();
        }
        return  null;
    }

}
