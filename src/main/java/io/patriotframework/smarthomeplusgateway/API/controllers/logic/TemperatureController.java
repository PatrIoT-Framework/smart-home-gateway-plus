package io.patriotframework.smarthomeplusgateway.API.controllers.logic;

import io.patriotframework.smarthomeplusgateway.APIVersions;
import io.patriotframework.smarthomeplusgateway.DTOs.TemperatureDTO;
import org.springframework.web.bind.annotation.*;
import io.patriotframework.smarthomeplusgateway.API.APIRoutes;

/**
 * Handles the GET, requests on Device route: {@link APIRoutes#TEMPERATURE_ROUTE}
 */
@RestController
public class TemperatureController {

    /**
     * Returns the temperature
     *
     * @param apiVersion api version specified in route
     * @return temperature
     */
    @GetMapping(APIRoutes.TEMPERATURE_ROUTE)
    public TemperatureDTO getTemperature(@PathVariable String apiVersion) {
        if(apiVersion.equals(APIVersions.V0_1)) {
            return new TemperatureDTO();
        }

        return  null;
    }

    /**
     *
     * Updates desired temperature
     *
     * @param temperature
     * @param apiVersion
     * @return
     */
    @PutMapping(APIRoutes.TEMPERATURE_ROUTE)
    public TemperatureDTO postTemperature(@RequestBody TemperatureDTO temperature, @PathVariable String apiVersion) {
        if(apiVersion.equals(APIVersions.V0_1)) {
            return temperature;
        }
        return  null;
    }
}
