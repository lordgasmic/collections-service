package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.MutableRepositoryItem;
import com.lordgasmic.collections.repository.RepositoryItem;
import com.lordgasmic.collections.wine.config.WineRatingConstants;
import com.lordgasmic.collections.wine.models.WineRatingRequest;
import com.lordgasmic.collections.wine.models.WineRatingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.lordgasmic.collections.wine.config.WineRatingConstants.PROPERTY_DATE;
import static com.lordgasmic.collections.wine.config.WineRatingConstants.PROPERTY_RATING;
import static com.lordgasmic.collections.wine.config.WineRatingConstants.PROPERTY_USER;
import static com.lordgasmic.collections.wine.config.WineRatingConstants.PROPERTY_WINE_ID;
import static com.lordgasmic.collections.wine.config.WineRatingConstants.WINE_RATING_REPOSITORY_ITEM;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class WineRatingService {
    private static final String REPO_NAME = "WineTastingRepository";

    private final GSARepository wineRepository;

    public WineRatingService() {
        wineRepository = (GSARepository) Nucleus.getInstance().getGenericService(REPO_NAME);
    }

    public List<WineRatingResponse> getAllWineRatings() throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_RATING_REPOSITORY_ITEM);
        return items.stream().map(WineRatingService::convertRepositoryItemToWineRatingResponse).collect(toList());
    }

    public WineRatingResponse getWineRatingByWineId(final int wineId) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_RATING_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(WineRatingConstants.PROPERTY_WINE_ID).equals(wineId))
                    .map(WineRatingService::convertRepositoryItemToWineRatingResponse)
                    .collect(toList())
                    .get(0);
    }

    public List<WineRatingResponse> getWineRatingsByUser(final String user) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_RATING_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(WineRatingConstants.PROPERTY_USER).equals(user))
                    .map(WineRatingService::convertRepositoryItemToWineRatingResponse)
                    .collect(toList());
    }

    public WineRatingResponse addWineRating(final WineRatingRequest request) throws SQLException {
        final MutableRepositoryItem item = wineRepository.createItem(WINE_RATING_REPOSITORY_ITEM);

        item.setProperty(PROPERTY_WINE_ID, request.getWineId());
        item.setProperty(PROPERTY_USER, request.getUser());
        item.setProperty(PROPERTY_DATE, request.getDate());
        item.setProperty(PROPERTY_RATING, request.getRating());

        final RepositoryItem addedItem = wineRepository.addItem(item);
        return convertRepositoryItemToWineRatingResponse(addedItem);
    }

    private static WineRatingResponse convertRepositoryItemToWineRatingResponse(final RepositoryItem repositoryItem) {
        final WineRatingResponse response = new WineRatingResponse();
        response.setWineId((Integer) repositoryItem.getPropertyValue(WineRatingConstants.PROPERTY_WINE_ID));
        response.setUser((String) repositoryItem.getPropertyValue(WineRatingConstants.PROPERTY_USER));
        response.setDate((String) repositoryItem.getPropertyValue(WineRatingConstants.PROPERTY_DATE));
        response.setRating((String) repositoryItem.getPropertyValue(WineRatingConstants.PROPERTY_RATING));
        return response;
    }
}
