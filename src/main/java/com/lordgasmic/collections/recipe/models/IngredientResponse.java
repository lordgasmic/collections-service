package com.lordgasmic.collections.recipe.models;

import lombok.Data;

@Data
public class IngredientResponse {
    private int ingredientId;
    private int recipeId;
    private int ordinal;
    private String quantity;
    private String uom;
    private String ingredient;
    private String preparation;
}
