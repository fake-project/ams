package com.ran.ams.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Data
@Builder
public class ConditionUpdateRequest {

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 50)
    private String name;
}
