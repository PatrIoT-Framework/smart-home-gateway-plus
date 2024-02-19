package io.patriotframework.smarthomeplusgateway.API.handlers;

import io.patriotframework.smarthomeplusgateway.DTOs.DeviceDTO;
import io.patriotframework.smarthomeplusgateway.Devices.Device;
import io.patriotframework.smarthomeplusgateway.FindStatus;
import io.patriotframework.smarthomeplusgateway.GateWay;
import io.patriotframework.smarthomeplusgateway.API.Mapper;

public class DeviceHandler {

    private final GateWay gateWay;
    private final Mapper mapper;

    public DeviceHandler(GateWay gateWay, Mapper mapper) {
        this.gateWay= gateWay;
        this.mapper = mapper;
    }

    public FindStatus handleAddDevice(DeviceDTO device) {
        if(device==null || device.getDeviceId()==null || device.getDeviceId().isEmpty()){
            throw new IllegalArgumentException("Device cannot be null");
        }
        return gateWay.findDevice(device.getDeviceId());
    }

    public boolean handleDeleteRequest(DeviceDTO device) {
        if(device==null || device.getDeviceId()==null || device.getDeviceId().isEmpty()){
            throw new IllegalArgumentException("Device cannot be null");
        }
        return gateWay.removeDevice(device.getDeviceId());
    }

    public DeviceDTO handleGetRequest(String label) {
        Device device = gateWay.getDevice(label);
        if(device==null){
            throw new IllegalArgumentException("Device not found");
        }
        return mapper.map(gateWay.getDevice(device.getDeviceId()), DeviceDTO.class);
    }

    public DeviceDTO handlePutRequest(DeviceDTO device) {
        if(device==null || device.getDeviceId()==null || device.getDeviceId().isEmpty()){
            throw new IllegalArgumentException("Device cannot be null");
        }
        gateWay.getDevice(device.getDeviceId()).update(device);
        return null;
    }
}
