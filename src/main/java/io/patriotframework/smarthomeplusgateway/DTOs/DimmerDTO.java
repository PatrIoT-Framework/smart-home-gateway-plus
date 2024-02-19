package io.patriotframework.smarthomeplusgateway.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DimmerDTO extends  LightDTO{
    private int brightness;

}
