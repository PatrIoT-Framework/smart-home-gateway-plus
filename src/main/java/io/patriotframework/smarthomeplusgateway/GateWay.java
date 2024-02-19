package io.patriotframework.smarthomeplusgateway;


import io.patriotframework.smarthomeplusgateway.DTO.HouseDTO;
import io.patriotframework.smarthomeplusgateway.house.House;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GateWay {

    private final Map<String, House> houses = new HashMap<>();

    private final ModelMapper modelMapper = new ModelMapper();

    public void addHouse(HouseDTO house) {
        houses.put(house.getName(), modelMapper.map(house,House.class));
    }

    public void removeHouse(String name) {
        houses.remove(name);
    }

    public HouseDTO getHouse(String name) {
        return modelMapper.map(houses.get(name),HouseDTO.class);
    }

}
