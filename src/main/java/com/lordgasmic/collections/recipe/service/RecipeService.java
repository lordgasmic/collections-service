package com.lordgasmic.collections.recipe.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.recipe.models.RecipeResponse;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.RepositoryItem;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.lordgasmic.collections.recipe.config.RecipeConstants.PROP_DESCRIPTION;
import static com.lordgasmic.collections.recipe.config.RecipeConstants.PROP_ID;
import static com.lordgasmic.collections.recipe.config.RecipeConstants.PROP_IMAGE;
import static com.lordgasmic.collections.recipe.config.RecipeConstants.PROP_NAME;
import static com.lordgasmic.collections.recipe.config.RecipeConstants.RECIPE_REPOSITORY;
import static com.lordgasmic.collections.recipe.config.RecipeConstants.RECIPE_REPOSITORY_ITEM;

@Service
public class RecipeService {

    private final GSARepository repository;

    public RecipeService() {
        repository = (GSARepository) Nucleus.getInstance().getGenericService(RECIPE_REPOSITORY);
    }

    public List<RecipeResponse> getAllRecipes() throws SQLException {
        final List<RepositoryItem> items = repository.getAllRepositoryItems(RECIPE_REPOSITORY_ITEM);
        return items.stream().map(RecipeService::convertRepositoryItemToRecipeResponse).collect(Collectors.toList());
    }

    public RecipeResponse getRecipe(int id) throws SQLException {
        RepositoryItem item = repository.getRepositoryItem(String.valueOf(id), RECIPE_REPOSITORY_ITEM);
        return convertRepositoryItemToRecipeResponse(item);
    }

    private static RecipeResponse convertRepositoryItemToRecipeResponse(RepositoryItem item) {
        RecipeResponse response = new RecipeResponse();

        response.setRecipeId((Integer) item.getPropertyValue(PROP_ID));
        response.setName((String) item.getPropertyValue(PROP_NAME));
        response.setDescription((String) item.getPropertyValue(PROP_DESCRIPTION));
        response.setImage((String) item.getPropertyValue(PROP_IMAGE));

        return response;
    }
}
