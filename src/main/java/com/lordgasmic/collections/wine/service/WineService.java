package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.RepositoryItem;
import com.lordgasmic.collections.wine.config.WineConstants;
import com.lordgasmic.collections.wine.models.WineResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.lordgasmic.collections.wine.config.WineConstants.PROPERTY_WINERY_ID;
import static com.lordgasmic.collections.wine.config.WineConstants.WINES_REPOSITORY_ITEM;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class WineService {
    private static final String REPO_NAME = "WineTastingRepository";

    private final GSARepository wineRepository;

    public WineService() {
        wineRepository = (GSARepository) Nucleus.getInstance().getGenericService(REPO_NAME);
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

    private static WineResponse convertRepositoryItemToWineResponse(final RepositoryItem repositoryItem) {
        final WineResponse response = new WineResponse();
        response.setId((Integer) repositoryItem.getPropertyValue(WineConstants.PROPERTY_ID));
        response.setWineryId((Integer) repositoryItem.getPropertyValue(WineConstants.PROPERTY_WINERY_ID));
        response.setName((String) repositoryItem.getPropertyValue(WineConstants.PROPERTY_NAME));
        response.setStyle((String) repositoryItem.getPropertyValue(WineConstants.PROPERTY_STYLE));
        return response;
    }
}
