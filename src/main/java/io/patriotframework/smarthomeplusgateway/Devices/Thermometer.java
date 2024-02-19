package io.patriotframework.smarthomeplusgateway.Devices;

public class Thermometer extends Sensor{
    private int temperature;

    public Thermometer(String deviceId) {
        super(deviceId);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
