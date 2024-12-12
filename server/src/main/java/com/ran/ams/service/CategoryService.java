package com.ran.ams.service;

import com.ran.ams.entity.Category;
import com.ran.ams.request.CategoryCreateRequest;
import com.ran.ams.request.CategoryUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

public interface CategoryService {
    Page<Category> findAll(int offset, int limit);
    void create(CategoryCreateRequest request);
    Category findById(int id);
    void update(int id, CategoryUpdateRequest request);
    void delete(int id);
}
