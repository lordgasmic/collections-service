package com.lordgasmic.collections.recipe.models;

import lombok.Data;

@Data
public class DirectionResponse {
    private int directionId;
    private int recipeId;
    private int ordinal;
    private String direction;
}
