package com.lordgasmic.collections.recipe.models;

import lombok.Data;

@Data
public class TagResponse {
    private int tagId;
    private int recipeId;
    private int ordinal;
    private String tag;
}
