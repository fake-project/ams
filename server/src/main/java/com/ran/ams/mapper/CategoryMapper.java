package com.ran.ams.mapper;

import com.ran.ams.dto.CategoryDto;
import com.ran.ams.entity.Category;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Component
public class CategoryMapper implements Function<Category, CategoryDto> {
    @Override
    public CategoryDto apply(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}
