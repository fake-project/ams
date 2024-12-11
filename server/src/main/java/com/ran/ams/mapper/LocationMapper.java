package com.ran.ams.mapper;

import com.ran.ams.dto.LocationDto;
import com.ran.ams.entity.Location;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Component
public class LocationMapper implements Function<Location, LocationDto> {
    @Override
    public LocationDto apply(Location location) {
        return new LocationDto(
                location.getId(),
                location.getName(),
                location.getCreatedAt(),
                location.getUpdatedAt()
        );
    }
}
