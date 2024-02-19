package io.patriotframework.smarthomeplusgateway;


import io.patriotframework.smarthomeplusgateway.DTOs.DevicesDTO;
import io.patriotframework.smarthomeplusgateway.Devices.Device;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GateWay {

    private final Map<String, Device> devices;


    public GateWay() {
        this.devices = null;
    }

    public boolean removeDevice(String deviceId) {

        if(devices.remove(deviceId)!= null){
            return true;
        }
        return false;
    }

    public FindStatus findDevice(String deviceId) {
        return FindStatus.FOUND;
    }

    public Device getDevice(String deviceId) {
        return devices.get(deviceId);
    }

    public DevicesDTO findDevices() {
        return null;
    }

    public DevicesDTO getDevices() {
        return null;
    }

}
