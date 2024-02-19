package io.patriotframework.smarthomeplusgateway.Devices;

import io.patriotframework.smarthomeplusgateway.DTOs.DeviceDTO;
import io.patriotframework.smarthomeplusgateway.DTOs.DevicesDTO;
import io.patriotframework.smarthomeplusgateway.DTOs.LightDTO;
import io.patriotframework.smarthomeplusgateway.API.Mapper;

public class Light extends Device {

    private boolean isOn;

    public Light(String deviceId) {
        super(deviceId);
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public DeviceDTO update(DeviceDTO device) {
        if(device instanceof LightDTO) {
            LightDTO light = (LightDTO) device;
            this.isOn = light.isOn();
        }
        super.update(device);
        LightDTO lightDTO =  Mapper.map(this, LightDTO.class);
        return   lightDTO;
    }
}
