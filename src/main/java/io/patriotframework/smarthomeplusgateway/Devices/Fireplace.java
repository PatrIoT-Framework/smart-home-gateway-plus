package io.patriotframework.smarthomeplusgateway.Devices;

public class Fireplace  extends Device {

        private boolean isOn;

        public Fireplace(String deviceId) {
            super(deviceId);
        }

        public boolean isOn() {
            return isOn;
        }

        public void setOn(boolean on) {
            isOn = on;
        }
}
