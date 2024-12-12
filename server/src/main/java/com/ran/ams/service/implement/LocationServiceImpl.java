package com.ran.ams.service.implement;

import com.ran.ams.entity.Location;
import com.ran.ams.repository.LocationRepository;
import com.ran.ams.request.LocationCreateRequest;
import com.ran.ams.request.LocationUpdateRequest;
import com.ran.ams.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Page<Location> findAll(int offset, int limit) {
        return locationRepository.findAll(PageRequest
                .of(offset, limit, Sort.by(Sort.Direction.DESC, "createdAt"))
        );
    }

    @Override
    public void create(LocationCreateRequest request) {
        Location location = new Location();
        location.setName(request.getName());

        locationRepository.save(location);
    }

    @Override
    public Location findById(int id) {
        return locationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void update(int id, LocationUpdateRequest request) {
        Location location = findById(id);

        location.setName(request.getName());
        locationRepository.save(location);
    }

    @Override
    public void delete(int id) {
        if (!locationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        locationRepository.deleteById(id);
    }
}
