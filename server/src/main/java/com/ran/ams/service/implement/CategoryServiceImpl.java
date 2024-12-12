package com.ran.ams.service.implement;

import com.ran.ams.entity.Category;
import com.ran.ams.repository.CategoryRepository;
import com.ran.ams.request.CategoryCreateRequest;
import com.ran.ams.request.CategoryUpdateRequest;
import com.ran.ams.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(int offset, int limit) {
        return categoryRepository.findAll(PageRequest
                .of(offset, limit, Sort.by(Sort.Direction.DESC, "createdAt"))
        );
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void create(CategoryCreateRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category name already exists");
        }

        Category category = new Category();
        category.setName(request.getName());

        categoryRepository.save(category);
    }

    @Override
    public void update(int id, CategoryUpdateRequest request) {
        Category category = findById(id);

        category.setName(request.getName());
        categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        categoryRepository.deleteById(id);
    }
}
