package com.kume.kume.presentation.mappers;

import com.kume.kume.application.dto.recipe.CreateRecipeIngredientRequest;
import com.kume.kume.infraestructure.models.RecipeIngredient;

public class RecipeIngredientMapper {
    public static RecipeIngredient toEntity(CreateRecipeIngredientRequest request) {
        if (request == null) {
            return null;
        }
        
        return RecipeIngredient.builder()
                .ingredient(request.getIngredient())
                .quantity(request.getQuantity())
                .build();
    }
}
