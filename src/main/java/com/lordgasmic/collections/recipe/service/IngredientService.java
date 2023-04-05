package com.lordgasmic.collections.recipe.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.recipe.config.RecipeConstants;
import com.lordgasmic.collections.recipe.models.IngredientResponse;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.RepositoryItem;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.lordgasmic.collections.recipe.config.IngredientConstants.PROP_INGREDIENT;
import static com.lordgasmic.collections.recipe.config.IngredientConstants.PROP_INGREDIENT_ID;
import static com.lordgasmic.collections.recipe.config.IngredientConstants.PROP_ORDINAL;
import static com.lordgasmic.collections.recipe.config.IngredientConstants.PROP_PREPARATION;
import static com.lordgasmic.collections.recipe.config.IngredientConstants.PROP_QUANTITY;
import static com.lordgasmic.collections.recipe.config.IngredientConstants.PROP_RECIPE_ID;
import static com.lordgasmic.collections.recipe.config.IngredientConstants.PROP_UOM;
import static com.lordgasmic.collections.recipe.config.IngredientConstants.REPOSITORY_ITEM;
import static java.util.stream.Collectors.toList;

@Service
public class IngredientService {

    private final GSARepository repository;

    public IngredientService() {
        repository = (GSARepository) Nucleus.getInstance().getGenericService(RecipeConstants.RECIPE_REPOSITORY);
    }

    public List<IngredientResponse> getIngredientsForRecipe(int recipeId) throws SQLException {
        List<RepositoryItem> items = repository.getRepositoryItems(String.valueOf(recipeId), PROP_RECIPE_ID, REPOSITORY_ITEM);
        return items.stream().map(IngredientService::convertRepositoryItemToIngredientResponse).collect(toList());
    }

    private static IngredientResponse convertRepositoryItemToIngredientResponse(RepositoryItem item) {
        IngredientResponse response = new IngredientResponse();

        response.setIngredientId((Integer) item.getPropertyValue(PROP_INGREDIENT_ID));
        response.setRecipeId((Integer) item.getPropertyValue(PROP_RECIPE_ID));
        response.setOrdinal((Integer) item.getPropertyValue(PROP_ORDINAL));
        response.setQuantity((String) item.getPropertyValue(PROP_QUANTITY));
        response.setUom((String) item.getPropertyValue(PROP_UOM));
        response.setIngredient((String) item.getPropertyValue(PROP_INGREDIENT));
        response.setPreparation((String) item.getPropertyValue(PROP_PREPARATION));

        return response;
    }
}
