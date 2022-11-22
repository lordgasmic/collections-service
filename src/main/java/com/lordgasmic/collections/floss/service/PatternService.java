package com.lordgasmic.collections.floss.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.floss.models.PatternResponse;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.RepositoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.lordgasmic.collections.floss.config.PatternConstants.ITEM_DESCRIPTOR;
import static com.lordgasmic.collections.floss.config.PatternConstants.PROP_CLOTH_TYPE;
import static com.lordgasmic.collections.floss.config.PatternConstants.PROP_FINISHED_SIZE;
import static com.lordgasmic.collections.floss.config.PatternConstants.PROP_NAME;
import static com.lordgasmic.collections.floss.config.PatternConstants.PROP_SIZE;
import static com.lordgasmic.collections.floss.config.PatternConstants.PROP_STITCH_COUNT;
import static com.lordgasmic.collections.floss.config.PatternConstants.PROP_THREADS_PER_STITCH;
import static com.lordgasmic.collections.floss.config.PatternConstants.PROP_TOTAL_COLORS;

@Service
@Slf4j
public class PatternService {

    public static final String FLOSS_REPOSITORY = "FlossRepository";

    private final GSARepository repository;

    public PatternService() {
        repository = (GSARepository) Nucleus.getInstance().getGenericService(FLOSS_REPOSITORY);
    }

    public List<PatternResponse> getAllPatterns() throws SQLException {
        final List<RepositoryItem> items = repository.getAllRepositoryItems(ITEM_DESCRIPTOR);
        return items.stream().map(PatternService::convertRepositoryItemToPatternResponse).collect(Collectors.toList());
    }

    //    public GasResponse addGas(final GasRequest request) throws SQLException {
    //        final MutableRepositoryItem item = repository.createItem(GAS_REPOSITORY_ITEM);
    //        item.setProperty(PROPERTY_DATE, request.getDate());
    //        item.setProperty(PROPERTY_ODOMETER, request.getOdometer());
    //        item.setProperty(PROPERTY_GAS, request.getGas());
    //        item.setProperty(PROPERTY_COST, request.getCost());
    //        item.setProperty(PROPERTY_VEHICLE, request.getVehicle());
    //        final RepositoryItem addedItem = repository.addItem(item);
    //
    //        return convertRepositoryItemToGasResponse(addedItem);
    //    }

    private static PatternResponse convertRepositoryItemToPatternResponse(final RepositoryItem repositoryItem) {
        final PatternResponse response = new PatternResponse();
        response.setName((String) repositoryItem.getPropertyValue(PROP_NAME));
        response.setSize((Integer) repositoryItem.getPropertyValue(PROP_SIZE));
        response.setClothType((String) repositoryItem.getPropertyValue(PROP_CLOTH_TYPE));
        response.setFinishedSize((String) repositoryItem.getPropertyValue(PROP_FINISHED_SIZE));
        response.setStitchCount((Integer) repositoryItem.getPropertyValue(PROP_STITCH_COUNT));
        response.setTotalColors((Integer) repositoryItem.getPropertyValue(PROP_TOTAL_COLORS));
        response.setThreadsPerStitch((Integer) repositoryItem.getPropertyValue(PROP_THREADS_PER_STITCH));
        return response;
    }
}
