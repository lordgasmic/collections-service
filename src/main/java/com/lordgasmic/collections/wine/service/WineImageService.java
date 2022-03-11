package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.MutableRepositoryItem;
import com.lordgasmic.collections.repository.RepositoryItem;
import com.lordgasmic.collections.wine.config.WineImageConstants;
import com.lordgasmic.collections.wine.models.WineImage;
import com.lordgasmic.collections.wine.models.WineImageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class WineImageService {
    private static final String REPO_NAME = "WineTastingRepository";

    private final GSARepository wineRepository;

    public WineImageService() {
        wineRepository = (GSARepository) Nucleus.getInstance().getGenericService(REPO_NAME);
    }

    public WineImageResponse addWineImage(final int wineId, final String label, final MultipartFile file) throws SQLException, IOException {
        final MutableRepositoryItem item = wineRepository.createItem(WineImageConstants.ITEM_DESCRIPTOR_NAME);
        item.setProperty(WineImageConstants.PROPERTY_WINE_ID, wineId);
        item.setProperty(WineImageConstants.PROPERTY_LABEL, label);
        item.setProperty(WineImageConstants.PROPERTY_IMAGE, file.getBytes());
        item.setProperty(WineImageConstants.PROPERTY_MIME_TYPE, file.getContentType());
        final RepositoryItem repositoryItem = wineRepository.addItem(item);

        final WineImageResponse response = new WineImageResponse();
        response.setWineImages(List.of(convertRepositoryItemToWineImage(repositoryItem)));
        return response;
    }

    public WineImageResponse getWineImages(final int wineId) throws SQLException {
        final List<RepositoryItem> items = wineRepository.getAllRepositoryItems(WineImageConstants.ITEM_DESCRIPTOR_NAME);
        final List<WineImage> images = items.stream()
                                            .filter(item -> item.getPropertyValue(WineImageConstants.PROPERTY_WINE_ID).equals(wineId))
                                            .map(WineImageService::convertRepositoryItemToWineImage)
                                            .collect(toList());
        final WineImageResponse response = new WineImageResponse();
        response.setWineImages(images);
        return response;
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
