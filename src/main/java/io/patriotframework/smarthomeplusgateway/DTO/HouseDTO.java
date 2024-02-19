package io.patriotframework.smarthomeplusgateway.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
/**
 * Data transfer object for house
 */
public class HouseDTO {

    /**
     * Name of the house
     */
    private String name;
    /**
     * Http address of the house
     */
    private String address;
}
