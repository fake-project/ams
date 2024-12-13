package com.ran.ams.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetUpdateRequest {

    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @NotBlank
    @Size(min = 5, max = 50)
    private String code;

    @NotBlank
    @JsonProperty("purchase_price")
    private Double purchasePrice;

    @NotBlank
    @JsonProperty("purchase_date")
    private LocalDate purchaseDate;

    @NotBlank
    @JsonProperty("validity_period")
    private LocalDate validityPeriod;

    @NotBlank
    @JsonProperty("category_id")
    private Integer categoryId;

    @NotBlank
    @JsonProperty("condition_id")
    private Integer conditionId;

    @NotBlank
    @JsonProperty("location_id")
    private Integer locationId;
}
