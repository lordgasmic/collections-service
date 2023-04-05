package com.lordgasmic.collections.recipe.controller;

import com.lordgasmic.collections.recipe.models.RecipeResponse;
import com.lordgasmic.collections.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class RecipeController {

    private final RecipeService service;

    @Autowired
    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/recipe")
    public List<RecipeResponse> getAllRecipes() throws SQLException {
        return service.getAllRecipes();
    }

    @GetMapping("/api/v1/recipe/{id}")
    public RecipeResponse getRecipe(@PathVariable int id) throws SQLException {
        return service.getRecipe(id);
    }
}
