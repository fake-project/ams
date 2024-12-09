package com.ran.ams.controller;

import com.ran.ams.entity.Category;
import com.ran.ams.request.CategoryCreateRequest;
import com.ran.ams.request.CategoryUpdateRequest;
import com.ran.ams.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> create(@Valid @RequestBody CategoryCreateRequest request) {
        categoryService.create(request);

        return ResponseEntity.ok("Category created");
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> findById(@PathVariable("id") int id){
        Category category = categoryService.findById(id);

        return ResponseEntity.ok(category);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> update(@Valid @PathVariable("id") int id, @RequestBody CategoryUpdateRequest request) {
        categoryService.update(id, request);

        return ResponseEntity.ok("Category updated");
    }

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> delete(@PathVariable int id) {
        categoryService.delete(id);

        return ResponseEntity.ok("Category deleted");
    }
}
