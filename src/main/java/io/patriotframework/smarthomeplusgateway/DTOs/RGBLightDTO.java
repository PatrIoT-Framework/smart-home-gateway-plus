package io.patriotframework.smarthomeplusgateway.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RGBLightDTO extends DimmerDTO{
    private int red;
    private int green;
    private int blue;
}
