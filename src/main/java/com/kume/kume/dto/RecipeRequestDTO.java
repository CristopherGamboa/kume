package com.kume.kume.dto;

import lombok.Data;

@Data
public class RecipeRequestDTO {
    private String title;
    private String description;
    private String ingredients;
    private String instructions;
}
