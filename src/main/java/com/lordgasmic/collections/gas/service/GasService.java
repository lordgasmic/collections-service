package com.lordgasmic.collections.gas.service;

import com.lordgasmic.collections.Nucleus;
import com.lordgasmic.collections.gas.models.GasRequest;
import com.lordgasmic.collections.gas.models.GasResponse;
import com.lordgasmic.collections.repository.GSARepository;
import com.lordgasmic.collections.repository.MutableRepositoryItem;
import com.lordgasmic.collections.repository.RepositoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.lordgasmic.collections.gas.config.GasConstants.GAS_REPOSITORY_ITEM;
import static com.lordgasmic.collections.gas.config.GasConstants.PROPERTY_COST;
import static com.lordgasmic.collections.gas.config.GasConstants.PROPERTY_DATE;
import static com.lordgasmic.collections.gas.config.GasConstants.PROPERTY_GAS;
import static com.lordgasmic.collections.gas.config.GasConstants.PROPERTY_ID;
import static com.lordgasmic.collections.gas.config.GasConstants.PROPERTY_ODOMETER;
import static com.lordgasmic.collections.gas.config.GasConstants.PROPERTY_VEHICLE;

@Service
@Slf4j
public class GasService {

    public static final String GAS_REPOSITORY = "GasRepository";

    private final GSARepository repository;

    public GasService() {
        repository = (GSARepository) Nucleus.getInstance().getGenericService(GAS_REPOSITORY);
    }

    public GasResponse addGas(final GasRequest request) throws SQLException {
        final MutableRepositoryItem item = repository.createItem(GAS_REPOSITORY_ITEM);
        item.setProperty(PROPERTY_DATE, request.getDate());
        item.setProperty(PROPERTY_ODOMETER, request.getOdometer());
        item.setProperty(PROPERTY_GAS, request.getGas());
        item.setProperty(PROPERTY_COST, request.getCost());
        item.setProperty(PROPERTY_VEHICLE, request.getVehicle());
        final RepositoryItem addedItem = repository.addItem(item);

        return convertRepositoryItemToGasResponse(addedItem);
    }

    public List<GasResponse> getGas(final String vehicle) throws SQLException {
        final List<RepositoryItem> items = repository.getRepositoryItems(vehicle, "vehicle", GAS_REPOSITORY_ITEM);
        return items.stream().map(GasService::convertRepositoryItemToGasResponse).collect(Collectors.toList());
    }

    private static GasResponse convertRepositoryItemToGasResponse(final RepositoryItem repositoryItem) {
        final GasResponse response = new GasResponse();
        response.setId((String) repositoryItem.getPropertyValue(PROPERTY_ID));
        response.setDate((String) repositoryItem.getPropertyValue(PROPERTY_DATE));
        response.setOdometer((String) repositoryItem.getPropertyValue(PROPERTY_ODOMETER));
        response.setGas((String) repositoryItem.getPropertyValue(PROPERTY_GAS));
        response.setCost((String) repositoryItem.getPropertyValue(PROPERTY_COST));
        response.setVehicle((String) repositoryItem.getPropertyValue(PROPERTY_VEHICLE));

        return response;
    }
}
