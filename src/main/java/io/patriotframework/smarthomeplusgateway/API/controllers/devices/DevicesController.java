package io.patriotframework.smarthomeplusgateway.API.controllers.devices;

import io.patriotframework.smarthomeplusgateway.API.APIRoutes;
import io.patriotframework.smarthomeplusgateway.API.Mapper;
import io.patriotframework.smarthomeplusgateway.API.handlers.DeviceHandler;
import io.patriotframework.smarthomeplusgateway.DTOs.DeviceDTO;
import io.patriotframework.smarthomeplusgateway.FindStatus;
import io.patriotframework.smarthomeplusgateway.GateWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DevicesController extends DeviceHandler {

    @Autowired
    public DevicesController(GateWay gateWay) {
        super(gateWay, new Mapper());
    }

    @PostMapping(APIRoutes.DEVICE_ROUTE)
    public FindStatus addDevice(@RequestBody DeviceDTO device) {
        if(device==null || device.getDeviceId()==null || device.getDeviceId().isEmpty()){
            throw new IllegalArgumentException("Device cannot be null");
        }
        return handleAddDevice(device);
    }

    @GetMapping(APIRoutes.DEVICE_ROUTE)
    public DeviceDTO getDevice(@PathVariable String label) {
        return  handleGetRequest(label);
    }
}
