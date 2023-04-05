package com.lordgasmic.collections.recipe.controller;

import com.lordgasmic.collections.recipe.models.IngredientResponse;
import com.lordgasmic.collections.recipe.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class IngredientController {

    private final IngredientService service;

    @Autowired
    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/ingredients/{recipeId}")
    public List<IngredientResponse> getIngredientsForRecipe(@PathVariable int recipeId) throws SQLException {
        return service.getIngredientsForRecipe(recipeId);
    }
}
