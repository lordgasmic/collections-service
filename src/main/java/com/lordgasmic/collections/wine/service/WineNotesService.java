package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.RepositoryItem;
import com.lordgasmic.collections.wine.config.WineNotesConstants;
import com.lordgasmic.collections.wine.config.WineRatingConstants;
import com.lordgasmic.collections.wine.models.WineNoteResponse;
import com.lordgasmic.collections.wine.models.WineRatingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.lordgasmic.collections.wine.config.WineNotesConstants.WINE_NOTES_REPOSITORY_ITEM;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class WineNotesService {
    private static final String REPO_NAME = "WineTastingRepository";

    private final GSARepository wineRepository;

    public WineNotesService() {
        wineRepository = (GSARepository) Nucleus.getInstance().getGenericService(REPO_NAME);
    }

    public List<WineNoteResponse> getAllWineNotes() throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_NOTES_REPOSITORY_ITEM);
        return items.stream().map(WineNotesService::convertRepositoryItemToWineNoteResponse).collect(toList());
    }

    public List<WineNoteResponse> getWineNotesByUser(final String user) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_NOTES_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(WineNotesConstants.PROPERTY_USER).equals(user))
                    .map(WineNotesService::convertRepositoryItemToWineNoteResponse)
                    .collect(toList());
    }

    public List<WineNoteResponse> getWineNotesByWineId(final int wineId) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WINE_NOTES_REPOSITORY_ITEM);
        return items.stream()
                    .filter(ri -> ri.getPropertyValue(WineNotesConstants.PROPERTY_WINE_ID).equals(wineId))
                    .map(WineNotesService::convertRepositoryItemToWineNoteResponse)
                    .collect(toList());
    }

    private static WineNoteResponse convertRepositoryItemToWineNoteResponse(final RepositoryItem repositoryItem) {
        final WineNoteResponse response = new WineNoteResponse();
        response.setId((Integer) repositoryItem.getPropertyValue(WineNotesConstants.PROPERTY_ID));
        response.setWineId((Integer) repositoryItem.getPropertyValue(WineNotesConstants.PROPERTY_WINE_ID));
        response.setUser((String) repositoryItem.getPropertyValue(WineNotesConstants.PROPERTY_USER));
        response.setNote((String) repositoryItem.getPropertyValue(WineNotesConstants.PROPERTY_NOTE));
        response.setOrdinal((Integer) repositoryItem.getPropertyValue(WineNotesConstants.PROPERTY_ORDINAL));
        response.setDate((String) repositoryItem.getPropertyValue(WineNotesConstants.PROPERTY_DATE));
        return response;
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
