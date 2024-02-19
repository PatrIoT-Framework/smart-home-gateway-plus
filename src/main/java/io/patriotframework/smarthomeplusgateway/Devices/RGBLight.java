package io.patriotframework.smarthomeplusgateway.Devices;

import io.patriotframework.smarthomeplusgateway.DTOs.RGBLightDTO;

public class RGBLight extends DimmerLight {

    private int red;
    private int green;
    private int blue;

    public RGBLight(String deviceId) {
        super(deviceId);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green; //get from the Virtual smart Home use Camel
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setRGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void update(RGBLightDTO rgbLightDTO) {
        this.red = rgbLightDTO.getRed();
        this.green = rgbLightDTO.getGreen();
        this.blue = rgbLightDTO.getBlue();
    }
}
