package com.kume.kume.presentation.mappers;

import com.kume.kume.application.dto.recipe.CreateStepRequest;
import com.kume.kume.infraestructure.models.Step;

public class StepMapper {
    public static Step toEntity(CreateStepRequest request) {
        if (request == null) {
            return null;
        }
        
        return Step.builder()
                .stepNumber(request.getStepNumber())
                .instruction(request.getInstruction())
                .build();
    }
}
