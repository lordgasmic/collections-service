package com.lordgasmic.collections.recipe.controller;

import com.lordgasmic.collections.recipe.models.TagResponse;
import com.lordgasmic.collections.recipe.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TagController {

    private final TagService service;

    @Autowired
    public TagController(final TagService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/tag/{recipeId}")
    public List<TagResponse> getTagsForRecipe(@PathVariable final int recipeId) throws SQLException {
        return service.getTagsForRecipeId(recipeId);
    }
}
