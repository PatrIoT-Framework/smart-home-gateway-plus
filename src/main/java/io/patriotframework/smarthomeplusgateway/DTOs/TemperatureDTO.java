package io.patriotframework.smarthomeplusgateway.DTOs;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for Temperature
 */
@Getter
@Setter
public class TemperatureDTO {
    /**
     * value of temperature in house
     */
    private double actualTemperature;

    /**
     * value of desired temperature in house
     */
    private double desiredTemperature;

}
