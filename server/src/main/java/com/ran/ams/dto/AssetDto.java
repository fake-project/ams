package com.ran.ams.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

public record AssetDto(
        int id,
        String name,
        String code,
        @JsonProperty("purchase_price")
        Double purchasePrice,
        @JsonProperty("purchase_date")
        LocalDate purchaseDate,
        @JsonProperty("validity_period")
        LocalDate validityPeriod,
        CategoryDto category,
        ConditionDto condition,
        LocationDto location,
        @JsonProperty("created_at")
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        LocalDateTime updatedAt
) {
}
