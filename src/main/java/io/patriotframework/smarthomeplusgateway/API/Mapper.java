package io.patriotframework.smarthomeplusgateway.API;

import io.patriotframework.smarthomeplusgateway.DTOs.DeviceDTO;
import io.patriotframework.smarthomeplusgateway.Devices.Device;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S,T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
