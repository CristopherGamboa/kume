package com.kume.kume.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RecipeResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String ingredients;
    private String instructions;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
