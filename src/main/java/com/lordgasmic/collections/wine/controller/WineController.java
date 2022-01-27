package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineryResponse;
import com.lordgasmic.collections.wine.service.WineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping("/api/v1/wineries")
    public List<WineryResponse> getWineries() throws SQLException {
        return wineService.getWineries();
    }

    @GetMapping("/api/v1/wineries/{id}")
    public WineryResponse getWineryById(@PathVariable final String id) throws SQLException {
        return wineService.getWineryById(id);
    }

    @GetMapping("/api/v1/wines")
    public Object getWines(@RequestParam("wineId") final Optional<String> wineId,
                           @RequestParam("wineryId") final Optional<String> wineryId) throws SQLException {
        log.info("wineid: " + wineId.get());
        log.info("wineid is empty: " + wineId.isEmpty());
        log.info("wineryId: " + wineryId.get());
        log.info("winerid: " + wineryId.isEmpty());
        if (wineId.isEmpty() && wineryId.isEmpty()) {
            return wineService.getAllWines();
        } else if (wineId.isPresent()) {
            return wineService.getWine(wineId.get());
        } else {
            return wineService.getWinesByWineryId(wineryId.get());
        }
    }
}
