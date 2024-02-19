package io.patriotframework.smarthomeplusgateway.services;

import io.patriotframework.smarthomeplusgateway.DTO.HouseDTO;
import io.patriotframework.smarthomeplusgateway.house.House;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.IOException;
import java.lang.module.FindException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for managing houses
 * This service provides methods for creating, updating, deleting and getting houses
 */
@Service("HouseService")
public class HouseServiceImpl implements HouseService {
    /**
     * A map that stores House objects, identified by their names.
     */
    private final Map<String, House> houses = new HashMap<>();

    /**
     * A ModelMapper object used for object-to-object mapping.
     */
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Adds a house to the map of houses.
     *
     * @param houseDTO a HouseDTO object representing the house to be added
     */
    public void createHouse(HouseDTO houseDTO) throws IllegalArgumentException, IOException {
        if (houseDTO != null) {
            if (houses.containsKey(houseDTO.getName())) {
                throw new KeyAlreadyExistsException("House already exists");
            } else if (houseDTO.getAddress().isEmpty()) {
                throw new IllegalArgumentException("House address is null");
            } else if (houseDTO.getName().isEmpty()) {
                throw new IllegalArgumentException("House name is null");
            } else {
                House house = modelMapper.map(houseDTO, House.class);
                this.houses.put(house.getName(), house);
            }
        } else {
            throw new IllegalArgumentException("HouseDTO is null");
        }
    }

    /**
     * Updates a house in the map of houses.
     *
     * @param houseDTO a HouseDTO object representing the house to be updated with new information
     * @throws IllegalArgumentException if the house does not exist or the HouseDTO object is null
     */
    @Override
    public void updateHouse(HouseDTO houseDTO, String name) throws IllegalArgumentException {
        if (houseDTO != null) {

            if (houseDTO.getName().isEmpty()) {
                throw new IllegalArgumentException("House name is null");
            } else if (houseDTO.getAddress().isEmpty()) {
                throw new IllegalArgumentException("House address is null");
            } else if (houses.containsKey(name)) {
                houses.get(name).update(houseDTO);
                houses.put(houseDTO.getName(), modelMapper.map(houseDTO, House.class));
                houses.remove(name);
            } else {
                throw new IllegalArgumentException("House does not exist");
            }
        } else {
            throw new IllegalArgumentException("HouseDTO is null");
        }

    }

    /**
     * Removes a house from the map of houses.
     *
     * @param name the name of the house to be removed
     * @throws IllegalArgumentException if the house does not exist or the house name is null
     * @throws IllegalArgumentException if the house name is null
     */
    public void deleteHouse(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("House name is null");
        }
        if (houses.containsKey(name)) {
            houses.remove(name);
        } else {
            throw new FindException("House does not exist");
        }
    }

    /**
     * Retrieves a house from the map of houses.
     *
     * @param name the name of the house to be retrieved
     * @return a HouseDTO object representing the retrieved house
     * @throws FindException            if the house does not exist
     * @throws IllegalArgumentException if the house name is null
     */
    public HouseDTO getHouse(String name) throws FindException, IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("House name is null");
        }
        if (houses.get(name) == null) {
            throw new FindException("House does not exist");
        } else {
            return modelMapper.map(houses.get(name), HouseDTO.class);
        }
    }

    /**
     * Retrieves all houses from the map of houses.
     *
     * @return a list of HouseDTO objects representing all houses in the map
     */
    @Override
    public List<HouseDTO> getAllHouses() {
        return this.houses.values().stream().map(house -> modelMapper.map(house, HouseDTO.class)).toList();
    }
}
