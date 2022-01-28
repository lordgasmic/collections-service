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
        if (wineId.isEmpty() && wineryId.isEmpty()) {
            return wineService.getAllWines();
        } else if (wineId.isPresent()) {
            return wineService.getWine(wineId.get());
        } else {
            return wineService.getWinesByWineryId(wineryId.get());
        }
    }

    @GetMapping("/api/v1/wineNotes")
    public Object getWineNotes(@RequestParam("user") final Optional<String> user,
                               @RequestParam("wineId") final Optional<Integer> wineId) throws SQLException {
        if (user.isEmpty() && wineId.isEmpty()) {
            return wineService.getAllWineNotes();
        } else if (wineId.isPresent()) {
            return wineService.getWineNotesByWineId(wineId.get());
        } else {
            return wineService.getWineNotesByUser(user.get());
        }
    }

    @GetMapping("/api/v1/wineRating")
    public Object getWineRating(@RequestParam("user") final Optional<String> user,
                                @RequestParam("wineId") final Optional<Integer> wineId) throws SQLException {
        if (user.isEmpty() && wineId.isEmpty()) {
            return wineService.getAllWineRatings();
        } else if (wineId.isPresent()) {
            return wineService.getWineRatingsByWineId(wineId.get());
        } else {
            return wineService.getWineRatingsByUser(user.get());
        }
    }
}
