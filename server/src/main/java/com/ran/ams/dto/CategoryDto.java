package com.ran.ams.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record CategoryDto(
        int id,
        String name,
        @JsonProperty("created_at")
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        LocalDateTime updatedAt
) {
}
