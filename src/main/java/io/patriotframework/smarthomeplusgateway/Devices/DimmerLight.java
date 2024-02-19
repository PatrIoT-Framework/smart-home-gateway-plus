package io.patriotframework.smarthomeplusgateway.Devices;

import io.patriotframework.smarthomeplusgateway.DTOs.DeviceDTO;
import io.patriotframework.smarthomeplusgateway.DTOs.DevicesDTO;
import io.patriotframework.smarthomeplusgateway.DTOs.DimmerDTO;

public class DimmerLight extends Light{

        private int brightness;

        public DimmerLight(String deviceId) {
            super(deviceId);
        }

        public int getBrightness() {
            return brightness;
        }

        public void setBrightness(int brightness) {
            this.brightness = brightness;
        }

        public DeviceDTO update(DeviceDTO device) {
            if(device instanceof DeviceDTO) {
                DimmerDTO light = (DimmerDTO) device;
                this.brightness = light.getBrightness();
            }
            super.update(device);
            return device;
        }
}
