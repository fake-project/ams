package com.ran.ams.controller;

import com.ran.ams.entity.Category;
import com.ran.ams.mapper.CategoryMapper;
import com.ran.ams.request.CategoryCreateRequest;
import com.ran.ams.request.CategoryUpdateRequest;
import com.ran.ams.response.WebDataResponse;
import com.ran.ams.response.WebResponse;
import com.ran.ams.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebDataResponse> findAll() {
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(
                WebDataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(categories.stream()
                                .map(categoryMapper)
                                .collect(Collectors.toList()))
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
    public ResponseEntity<WebDataResponse> findById(@PathVariable("id") int id){
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
    public ResponseEntity<WebResponse> update(@Valid @PathVariable("id") int id, @RequestBody CategoryUpdateRequest request) {
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
