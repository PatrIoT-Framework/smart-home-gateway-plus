package io.patriotframework.smarthomeplusgateway.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightDTO extends DeviceDTO{

    private boolean isOn;
}
