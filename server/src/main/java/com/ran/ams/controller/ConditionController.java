package com.ran.ams.controller;

import com.ran.ams.entity.Condition;
import com.ran.ams.mapper.ConditionMapper;
import com.ran.ams.request.ConditionCreateRequest;
import com.ran.ams.request.ConditionUpdateRequest;
import com.ran.ams.response.WebDataResponse;
import com.ran.ams.response.WebResponse;
import com.ran.ams.service.ConditionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/condition")
public class ConditionController {

    private final ConditionService conditionService;
    private final ConditionMapper conditionMapper;

    @GetMapping(
            path = "/{offset}/{limit}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse> findAll(@PathVariable int offset, @PathVariable int limit) {
        Page<Condition> conditions = conditionService.findAll(offset, limit);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebDataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(conditions.map(conditionMapper))
                        .build()
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> create(@Valid @RequestBody ConditionCreateRequest request) {
        conditionService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                WebResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED.getReasonPhrase())
                        .message("Condition created")
                        .build()
        );
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse> findById(@PathVariable int id) {
        Condition condition = conditionService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebDataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(conditionMapper.apply(condition))
                        .build()
        );
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> update(@PathVariable int id, @Valid @RequestBody ConditionUpdateRequest request) {
        conditionService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .message("Condition updated")
                        .build()
        );
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> delete(@PathVariable int id) {
        conditionService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .message("Condition deleted")
                        .build()
        );
    }
}
