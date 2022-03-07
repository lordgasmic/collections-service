package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineRequest;
import com.lordgasmic.collections.wine.service.WineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@Slf4j
public class WineController {

    @Autowired
    private WineService service;

    @GetMapping("/api/v1/wines")
    public Object getWines(@RequestParam("wineId") final Optional<String> wineId,
                           @RequestParam("wineryId") final Optional<String> wineryId) throws SQLException {
        if (wineId.isEmpty() && wineryId.isEmpty()) {
            return service.getAllWines();
        } else if (wineId.isPresent()) {
            return service.getWine(wineId.get());
        } else {
            return service.getWinesByWineryId(wineryId.get());
        }
    }

    @PutMapping("/api/v1/wines")
    public void addWine(@RequestBody final WineRequest wineRequest) {

    }
}
