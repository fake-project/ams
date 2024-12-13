package com.ran.ams.controller;

import com.ran.ams.dto.PageableDto;
import com.ran.ams.entity.Asset;
import com.ran.ams.mapper.AssetMapper;
import com.ran.ams.request.AssetCreateRequest;
import com.ran.ams.request.AssetUpdateRequest;
import com.ran.ams.response.WebDataResponse;
import com.ran.ams.response.WebResponse;
import com.ran.ams.service.AssetService;
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
@RequestMapping("/asset")
public class AssetController {
    private final AssetService assetService;
    private final AssetMapper assetMapper;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse<Object>> findAll(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        Page<Asset> assets = assetService.findAll(offset, limit);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebDataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(PageableDto.builder()
                                .currentPage(offset)
                                .perPage(limit)
                                .isFirst(assets.isFirst())
                                .isLast(assets.isLast())
                                .total(assets.getTotalPages())
                                .content(assets.getContent().stream().map(assetMapper))
                                .build())
                        .build()
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> create(@RequestBody AssetCreateRequest request) {
        assetService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                WebResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED.getReasonPhrase())
                        .message("Asset created")
                        .build()
        );
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse<Object>> findById(@PathVariable Integer id) {
        Asset asset = assetService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                WebDataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(assetMapper.apply(asset))
                        .build()
        );
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> update(@PathVariable Integer id, @RequestBody AssetUpdateRequest request) {
        assetService.update(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                WebResponse.builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .status(HttpStatus.ACCEPTED.getReasonPhrase())
                        .message("Asset updated")
                        .build()
        );
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> delete(@PathVariable Integer id) {
        assetService.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                WebResponse.builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .status(HttpStatus.ACCEPTED.getReasonPhrase())
                        .message("Asset deleted")
                        .build()
        );
    }
}
