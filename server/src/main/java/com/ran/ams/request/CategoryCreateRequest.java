package com.ran.ams.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreateRequest {
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 50)
    private String name;
}
