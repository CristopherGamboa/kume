package com.kume.kume.presentation.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
    /**
     * Mapea la URL privada para listar/visualizar recetas.
     * Solo accesible si el usuario está autenticado.
     */
    @GetMapping("/recipes")
    public String privateRecipesList(Model model) {

        model.addAttribute("navbarItems", List.of(
                Map.of("label", "Inicio", "url", "/home"),
                Map.of("label", "Cerrar Sesión", "url", "/auth/logout")));
        model.addAttribute("toastMessage", "Bienvenido, has iniciado sesión correctamente.");
        model.addAttribute("toastType", "success");
        return "recipe/recipe-list";
    }
}
