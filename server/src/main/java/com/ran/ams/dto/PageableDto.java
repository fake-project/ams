package com.ran.ams.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://linkedin.com/in/riyan-amanda">...</a>
 * @since 12/12/2024, Thursday
 **/

@Builder
public record PageableDto(
        @JsonProperty("current_page")
        int currentPage,
        @JsonProperty("per_page")
        int perPage,
        @JsonProperty("is_first")
        boolean isFirst,
        @JsonProperty("is_last")
        boolean isLast,
        int total,
        Object content
) {
}
