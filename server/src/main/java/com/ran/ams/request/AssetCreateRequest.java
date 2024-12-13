package com.ran.ams.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class AssetCreateRequest {
    @NotBlank(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @NotNull(message = "Name is required")
    @Size(min = 5, max = 50)
    private String name;

    @NotBlank(message = "Code is required")
    @NotEmpty(message = "Code is required")
    @NotNull(message = "Code is required")
    @Size(min = 5, max = 50)
    private String code;

    @NotBlank(message = "Purchase Price is required")
    @NotEmpty(message = "Purchase Price is required")
    @NotNull(message = "Purchase Price is required")
    @JsonProperty("purchase_price")
    private Double purchasePrice;

    @NotBlank(message = "Purchase Date is required")
    @NotEmpty(message = "Purchase Date is required")
    @NotNull(message = "Purchase Date is required")
    @JsonProperty("purchase_date")
    private LocalDate purchaseDate;

    @NotBlank(message = "Validity Period is required")
    @NotEmpty(message = "Validity Period is required")
    @NotNull(message = "Validity Period is required")
    @JsonProperty("validity_period")
    private LocalDate validityPeriod;

    @NotEmpty(message = "Validity Period is required")
    @NotNull(message = "Validity Period is required")
    @JsonProperty("category_id")
    private Integer categoryId;

    @NotNull(message = "Validity Period is required")
    @JsonProperty("condition_id")
    private Integer conditionId;

    @NotNull(message = "Validity Period is required")
    @JsonProperty("location_id")
    private Integer locationId;
}
