package io.patriotframework.smarthomeplusgateway.Devices;

public abstract class Sensor extends  Device {
    private boolean isOn;

    public Sensor(String deviceId) {
        super(deviceId);
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
