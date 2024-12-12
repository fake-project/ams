package com.ran.ams.controller;

import com.ran.ams.dto.PageableDto;
import com.ran.ams.entity.Category;
import com.ran.ams.mapper.CategoryMapper;
import com.ran.ams.request.CategoryCreateRequest;
import com.ran.ams.request.CategoryUpdateRequest;
import com.ran.ams.response.WebDataResponse;
import com.ran.ams.response.WebResponse;
import com.ran.ams.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse<Object>> findAll(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        Page<Category> categories = categoryService.findAll(offset, limit);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebDataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(PageableDto.builder()
                                .currentPage(offset)
                                .perPage(limit)
                                .isFirst(categories.isFirst())
                                .isLast(categories.isLast())
                                .total(categories.getTotalPages())
                                .content(categories.getContent().stream().map(categoryMapper))
                                .build())
                        .build()
        );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> create(@Valid @RequestBody CategoryCreateRequest request) {
        categoryService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                WebResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED.getReasonPhrase())
                        .message("Category created")
                        .build());
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse<Object>> findById(@PathVariable int id) {
        Category category = categoryService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(WebDataResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryMapper.apply(category))
                .build());
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> update(@Valid @PathVariable int id, @RequestBody CategoryUpdateRequest request) {
        categoryService.update(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(WebResponse.builder()
                .code(HttpStatus.ACCEPTED.value())
                .status(HttpStatus.ACCEPTED.getReasonPhrase())
                .message("Category updated")
                .build());
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse> delete(@PathVariable int id) {
        categoryService.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(WebResponse.builder()
                .code(HttpStatus.ACCEPTED.value())
                .status(HttpStatus.ACCEPTED.getReasonPhrase())
                .message("Category deleted")
                .build());
    }
}
