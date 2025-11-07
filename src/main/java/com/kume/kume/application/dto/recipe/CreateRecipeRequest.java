package com.kume.kume.application.dto.recipe;

import java.util.Set;

import com.kume.kume.infraestructure.models.DifficultyLevel;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequest {
    @NotBlank(message = "El nombre de la receta es obligatorio")
    private String name;
    @NotBlank(message = "El tiempo de cocina es obligatorio")
    private Long cookingTime;
    @NotBlank(message = "La dificultad de la receta es obligatoria")
    private DifficultyLevel difficulty;
    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String imageUrl;
    @NotBlank(message = "Los ingredientes son obligatorios")
    private Set<CreateRecipeIngredientRequest> ingredients;
    @NotBlank(message = "Los pasos son obligatorios")
    private Set<CreateStepRequest> steps;
}
