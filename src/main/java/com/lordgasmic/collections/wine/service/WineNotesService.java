package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.MutableRepositoryItem;
import com.lordgasmic.collections.repository.RepositoryItem;
import com.lordgasmic.collections.wine.config.WineNotesConstants;
import com.lordgasmic.collections.wine.models.WineNoteRequest;
import com.lordgasmic.collections.wine.models.WineNoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.lordgasmic.collections.wine.config.WineNotesConstants.PROPERTY_DATE;
import static com.lordgasmic.collections.wine.config.WineNotesConstants.PROPERTY_NOTE;
import static com.lordgasmic.collections.wine.config.WineNotesConstants.PROPERTY_ORDINAL;
import static com.lordgasmic.collections.wine.config.WineNotesConstants.PROPERTY_USER;
import static com.lordgasmic.collections.wine.config.WineNotesConstants.PROPERTY_WINE_ID;
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

    public List<WineNoteResponse> addWineNotes(final List<WineNoteRequest> requests) {
        return requests.stream()
                       .map(this::createItem)
                       .map(this::addItem)
                       .map(WineNotesService::convertRepositoryItemToWineNoteResponse)
                       .collect(toList());
    }

    private MutableRepositoryItem createItem(final WineNoteRequest request) {
        final MutableRepositoryItem item = wineRepository.createItem(WINE_NOTES_REPOSITORY_ITEM);
        item.setProperty(PROPERTY_WINE_ID, request.getWineId());
        item.setProperty(PROPERTY_USER, request.getUser());
        item.setProperty(PROPERTY_NOTE, request.getNote());
        item.setProperty(PROPERTY_ORDINAL, request.getOrdinal());
        item.setProperty(PROPERTY_DATE, request.getDate());

        return item;
    }

    private RepositoryItem addItem(final MutableRepositoryItem item) {
        try {
            return wineRepository.addItem(item);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
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
}
