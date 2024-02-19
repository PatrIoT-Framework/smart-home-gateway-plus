package io.patriotframework.smarthomeplusgateway.Devices;

import io.patriotframework.smarthomeplusgateway.DTOs.DeviceDTO;
import io.patriotframework.smarthomeplusgateway.DTOs.DevicesDTO;

public abstract class Device {

    private String deviceId;

    //private String room;

    public Device(String deviceId) {
        if(deviceId != null && !deviceId.isEmpty()) {
            this.deviceId = deviceId;
        } else {
            throw new IllegalArgumentException("Device id can't be null or empty");
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public DeviceDTO update(DeviceDTO device) {
        return null;
    }
}
