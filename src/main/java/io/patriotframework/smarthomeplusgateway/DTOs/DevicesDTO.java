package io.patriotframework.smarthomeplusgateway.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DevicesDTO {

    private List<? extends DeviceDTO> devices;
}
