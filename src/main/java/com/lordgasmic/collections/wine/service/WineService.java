package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.RepositoryItem;
import com.lordgasmic.collections.wine.models.WineNoteResponse;
import com.lordgasmic.collections.wine.models.WineRatingResponse;
import com.lordgasmic.collections.wine.models.WineResponse;
import com.lordgasmic.collections.wine.models.WineryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class WineService {
    private static final String REPO_NAME = "WineTastingRepository";
    private static final String WINERY_REPOSITORY_ITEM = "winery";
    private static final String WINES_REPOSITORY_ITEM = "wines";
    private static final String WINE_NOTES_REPOSITORY_ITEM = "wineNotes";
    private static final String WINE_RATING_REPOSITORY_ITEM = "wineRating";
    private static final String PROPERTY_ID = "id";
    private static final String PROPERTY_WINERY_ID = "wineryId";
    private static final String PROPERTY_WINE_ID = "wineId";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_LOCATION = "location";
    private static final String PROPERTY_STYLE = "style";
    private static final String PROPERTY_USER = "user";
    private static final String PROPERTY_ORDINAL = "ordinal";
    private static final String PROPERTY_DATE = "date";
    private static final String PROPERTY_RATING = "rating";
    private static final String PROPERTY_NOTE = "note";

    private final GSARepository wineRepository;

    public WineService() {
        wineRepository = (GSARepository) Nucleus.getInstance().getGenericService(REPO_NAME);
    }

    public List<WineryResponse> getWineries() throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINERY_REPOSITORY_ITEM);
        return items.stream().map(WineService::convertRepositoryItemToWineryResponse).collect(toList());
    }

    public WineryResponse getWineryById(final String id) throws SQLException {
        final RepositoryItem winery = wineRepository.getRepositoryItem(id, WINERY_REPOSITORY_ITEM);
        return convertRepositoryItemToWineryResponse(winery);
    }

    public List<WineResponse> getAllWines() throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINES_REPOSITORY_ITEM);
        return items.stream().map(WineService::convertRepositoryItemToWineResponse).collect(toList());
    }

    public List<WineResponse> getWinesByWineryId(final String id) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINES_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(PROPERTY_WINERY_ID).equals(Integer.parseInt(id)))
                    .map(WineService::convertRepositoryItemToWineResponse)
                    .collect(toList());
    }

    public WineResponse getWine(final String id) throws SQLException {
        final RepositoryItem item = wineRepository.getRepositoryItem(id, WINES_REPOSITORY_ITEM);
        return convertRepositoryItemToWineResponse(item);
    }

    public List<WineNoteResponse> getAllWineNotes() throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_NOTES_REPOSITORY_ITEM);
        return items.stream().map(WineService::convertRepositoryItemToWineNoteResponse).collect(toList());
    }

    public List<WineNoteResponse> getWineNotesByUser(final String user) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_NOTES_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(PROPERTY_USER).equals(user))
                    .map(WineService::convertRepositoryItemToWineNoteResponse)
                    .collect(toList());
    }

    public List<WineNoteResponse> getWineNotesByWineId(final int wineId) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_NOTES_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(PROPERTY_WINE_ID).equals(wineId))
                    .map(WineService::convertRepositoryItemToWineNoteResponse)
                    .collect(toList());
    }

    public List<WineRatingResponse> getAllWineRatings() throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_RATING_REPOSITORY_ITEM);
        return items.stream().map(WineService::convertRepositoryItemToWineRatingResponse).collect(toList());
    }

    public WineRatingResponse getWineRatingByWineId(final int wineId) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_RATING_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(PROPERTY_WINE_ID).equals(wineId))
                    .map(WineService::convertRepositoryItemToWineRatingResponse)
                    .collect(toList())
                    .get(0);
    }

    public List<WineRatingResponse> getWineRatingsByUser(final String user) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_RATING_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(PROPERTY_USER).equals(user))
                    .map(WineService::convertRepositoryItemToWineRatingResponse)
                    .collect(toList());
    }

    private static WineryResponse convertRepositoryItemToWineryResponse(final RepositoryItem repositoryItem) {
        final WineryResponse response = new WineryResponse();
        response.setId((Integer) repositoryItem.getPropertyValue(PROPERTY_ID));
        response.setName((String) repositoryItem.getPropertyValue(PROPERTY_NAME));
        response.setLocation((String) repositoryItem.getPropertyValue(PROPERTY_LOCATION));

        return response;
    }

    private static WineResponse convertRepositoryItemToWineResponse(final RepositoryItem repositoryItem) {
        final WineResponse response = new WineResponse();
        response.setId((Integer) repositoryItem.getPropertyValue(PROPERTY_ID));
        response.setWineryId((Integer) repositoryItem.getPropertyValue(PROPERTY_WINERY_ID));
        response.setName((String) repositoryItem.getPropertyValue(PROPERTY_NAME));
        response.setStyle((String) repositoryItem.getPropertyValue(PROPERTY_STYLE));
        return response;
    }

    private static WineNoteResponse convertRepositoryItemToWineNoteResponse(final RepositoryItem repositoryItem) {
        final WineNoteResponse response = new WineNoteResponse();
        response.setId((Integer) repositoryItem.getPropertyValue(PROPERTY_ID));
        response.setWineId((Integer) repositoryItem.getPropertyValue(PROPERTY_WINE_ID));
        response.setUser((String) repositoryItem.getPropertyValue(PROPERTY_USER));
        response.setNote((String) repositoryItem.getPropertyValue(PROPERTY_NOTE));
        response.setOrdinal((Integer) repositoryItem.getPropertyValue(PROPERTY_ORDINAL));
        response.setDate((String) repositoryItem.getPropertyValue(PROPERTY_DATE));
        return response;
    }

    private static WineRatingResponse convertRepositoryItemToWineRatingResponse(final RepositoryItem repositoryItem) {
        final WineRatingResponse response = new WineRatingResponse();
        response.setWineId((Integer) repositoryItem.getPropertyValue(PROPERTY_WINE_ID));
        response.setUser((String) repositoryItem.getPropertyValue(PROPERTY_USER));
        response.setDate((String) repositoryItem.getPropertyValue(PROPERTY_DATE));
        response.setRating((String) repositoryItem.getPropertyValue(PROPERTY_RATING));
        return response;
    }

}
