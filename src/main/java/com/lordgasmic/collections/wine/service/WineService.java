package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.RepositoryItem;
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
    private static final String PROPERTY_ID = "id";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_LOCATION = "location";

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

    private static WineryResponse convertRepositoryItemToWineryResponse(final RepositoryItem repositoryItem) {
        final WineryResponse response = new WineryResponse();
        response.setId(Integer.parseInt((String) repositoryItem.getPropertyValue(PROPERTY_ID)));
        response.setName((String) repositoryItem.getPropertyValue(PROPERTY_NAME));
        response.setLocation((String) repositoryItem.getPropertyValue(PROPERTY_LOCATION));

        return response;
    }

}
