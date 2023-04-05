package com.lordgasmic.collections.recipe.models;

import lombok.Data;

@Data
public class RecipeResponse {
    private int recipeId;
    private String name;
    private String description;
    private String image;
}
