package com.ran.ams.service;

import com.ran.ams.entity.Location;
import com.ran.ams.request.LocationCreateRequest;
import com.ran.ams.request.LocationUpdateRequest;

import java.util.List;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

public interface LocationService {
    List<Location> findAll();
    void create(LocationCreateRequest request);
    Location findById(int id);
    void update(int id, LocationUpdateRequest request);
    void delete(int id);
}
