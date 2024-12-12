package com.ran.ams.controller;

import com.ran.ams.dto.PageableDto;
import com.ran.ams.entity.Location;
import com.ran.ams.mapper.LocationMapper;
import com.ran.ams.request.LocationCreateRequest;
import com.ran.ams.request.LocationUpdateRequest;
import com.ran.ams.response.WebDataResponse;
import com.ran.ams.response.WebResponse;
import com.ran.ams.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse<Object>> findAll(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        Page<Location> locations = locationService.findAll(offset, limit);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebDataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(PageableDto.builder()
                                .currentPage(offset)
                                .perPage(limit)
                                .isFirst(locations.isFirst())
                                .isLast(locations.isLast())
                                .total(locations.getTotalPages())
                                .content(locations.getContent().stream().map(locationMapper))
                                .build())
                        .build()
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> create(@Valid @RequestBody LocationCreateRequest request) {
        locationService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                WebResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED.getReasonPhrase())
                        .message("Location created")
                        .build());
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse<Object>> findById(@PathVariable int id) {
        Location location = locationService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebDataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(locationMapper.apply(location))
                        .build()
        );
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> update(@Valid @PathVariable int id, @RequestBody LocationUpdateRequest request) {
        locationService.update(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                WebResponse.builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .status(HttpStatus.ACCEPTED.getReasonPhrase())
                        .message("Location updated")
                        .build()
        );
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> delete(@PathVariable int id) {
        locationService.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                WebResponse.builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .status(HttpStatus.ACCEPTED.getReasonPhrase())
                        .message("Location deleted")
                        .build()
        );
    }

}
