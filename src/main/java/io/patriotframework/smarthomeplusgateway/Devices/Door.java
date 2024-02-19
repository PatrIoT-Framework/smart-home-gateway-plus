package io.patriotframework.smarthomeplusgateway.Devices;

public class Door extends  Device{
    private boolean isLocked;

    public Door(String deviceId) {
        super(deviceId);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
