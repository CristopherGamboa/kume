package com.kume.kume.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kume.kume.models.Recipe;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByUserId(Long userId);
}