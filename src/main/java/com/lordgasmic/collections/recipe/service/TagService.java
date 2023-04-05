package com.lordgasmic.collections.recipe.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.recipe.config.RecipeConstants;
import com.lordgasmic.collections.recipe.models.TagResponse;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.RepositoryItem;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.lordgasmic.collections.recipe.config.TagConstants.PROP_ORDINAL;
import static com.lordgasmic.collections.recipe.config.TagConstants.PROP_RECIPE_ID;
import static com.lordgasmic.collections.recipe.config.TagConstants.PROP_TAG;
import static com.lordgasmic.collections.recipe.config.TagConstants.PROP_TAG_ID;
import static com.lordgasmic.collections.recipe.config.TagConstants.REPOSITORY_ITEM;
import static java.util.stream.Collectors.toList;

@Service
public class TagService {

    private final GSARepository repository;

    public TagService() {
        repository = (GSARepository) Nucleus.getInstance().getGenericService(RecipeConstants.RECIPE_REPOSITORY);
    }

    public List<TagResponse> getTagsForRecipeId(int recipeId) throws SQLException {
        List<RepositoryItem> items = repository.getRepositoryItems(String.valueOf(recipeId), PROP_RECIPE_ID, REPOSITORY_ITEM);
        return items.stream().map(TagService::convertRepositoryItemToTagResponse).collect(toList());
    }

    private static TagResponse convertRepositoryItemToTagResponse(RepositoryItem item) {
        TagResponse response = new TagResponse();

        response.setTagId((Integer) item.getPropertyValue(PROP_TAG_ID));
        response.setRecipeId((Integer) item.getPropertyValue(PROP_RECIPE_ID));
        response.setOrdinal((Integer) item.getPropertyValue(PROP_ORDINAL));
        response.setTag((String) item.getPropertyValue(PROP_TAG));

        return response;
    }

}
