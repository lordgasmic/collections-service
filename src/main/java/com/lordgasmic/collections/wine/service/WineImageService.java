package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.MutableRepositoryItem;
import com.lordgasmic.collections.repository.RepositoryItem;
import com.lordgasmic.collections.wine.config.WineImageConstants;
import com.lordgasmic.collections.wine.models.WineImage;
import com.lordgasmic.collections.wine.models.WineImageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class WineImageService {
    private static final String REPO_NAME = "WineTastingRepository";

    private final GSARepository wineRepository;

    public WineImageService() {
        wineRepository = (GSARepository) Nucleus.getInstance().getGenericService(REPO_NAME);
    }

    public Object addWineImage(final WineImageRequest request) throws SQLException {
        final MutableRepositoryItem item = wineRepository.createItem(WineImageConstants.ITEM_DESCRIPTOR_NAME);
        item.setProperty(WineImageConstants.PROPERTY_WINE_ID, request.getWineId());
        item.setProperty(WineImageConstants.PROPERTY_LABEL, request.getWineId());
        item.setProperty(WineImageConstants.PROPERTY_IMAGE, request.getWineId());
        item.setProperty(WineImageConstants.PROPERTY_MIME_TYPE, request.getWineId());
        final RepositoryItem repositoryItem = wineRepository.addItem(item);

        return List.of(convertRepositoryItemToWineImage(repositoryItem));
    }

    private static WineImage convertRepositoryItemToWineImage(final RepositoryItem repositoryItem) {
        final WineImage image = new WineImage();
        image.setId((Integer) repositoryItem.getPropertyValue(WineImageConstants.PROPERTY_ID));
        image.setWineId((Integer) repositoryItem.getPropertyValue(WineImageConstants.PROPERTY_WINE_ID));
        image.setLabel((String) repositoryItem.getPropertyValue(WineImageConstants.PROPERTY_LABEL));
        image.setImage((byte[]) repositoryItem.getPropertyValue(WineImageConstants.PROPERTY_IMAGE));
        image.setMimeType((String) repositoryItem.getPropertyValue(WineImageConstants.PROPERTY_MIME_TYPE));
        return image;
    }
}
