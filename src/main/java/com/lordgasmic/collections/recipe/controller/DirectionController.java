package com.lordgasmic.collections.recipe.controller;

import com.lordgasmic.collections.recipe.models.DirectionResponse;
import com.lordgasmic.collections.recipe.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class DirectionController {

    private final DirectionService service;

    @Autowired
    public DirectionController(DirectionService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/direction/{recipeId}")
    public List<DirectionResponse> getDirectionsForRecipeId(@PathVariable int recipeId) throws SQLException {
        return service.getDirectionsForRecipe(recipeId);
    }
}
