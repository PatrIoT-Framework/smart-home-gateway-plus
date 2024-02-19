package io.patriotframework.smarthomeplusgateway.API;

public class APIRoutes {

    public static final String API_VERSION = "v0.1";
    /**
    * Beginning of the all api routes. All api endpoints share this prefix.
    */
    public static final String API_ROUTE = "/api/{apiVersion}/";

    /**
     * Route of the temperature.
     */
    public static final String TEMPERATURE_ROUTE = API_ROUTE + "temperature/";

    public static final String GATEWAY_ROUTE = API_ROUTE + "gateway/";
    /**
     * Route of the temperature.
     */
    public static final String LOCK_ROUTE = GATEWAY_ROUTE + "lock/";
    public static final String LIGHT_ROUTE = GATEWAY_ROUTE + "light/";
    public static final String DIMMER_LIGHT_ROUTE = GATEWAY_ROUTE + "dimmerlight/";
    public static final String RGB_LIGHT_ROUTE = GATEWAY_ROUTE + "rgblight/{label}";
    public static final String DOOR_ROUTE = GATEWAY_ROUTE + "door/";
    public static final String THERMOMETER_ROUTE = GATEWAY_ROUTE + "thermometer/";
    public static final String DEVICE_ROUTE = GATEWAY_ROUTE + "device/";
    public static final String FIND_NEW_DEVICES =  GATEWAY_ROUTE + "find/";

}
