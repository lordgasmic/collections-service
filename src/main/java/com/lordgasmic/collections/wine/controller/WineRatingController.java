package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineRatingRequest;
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
public class WineRatingController {

    @Autowired
    private WineService wineService;

    @GetMapping("/api/v1/wineRating")
    public Object getWineRating(@RequestParam("user") final Optional<String> user,
                                @RequestParam("wineId") final Optional<Integer> wineId) throws SQLException {
        if (user.isEmpty() && wineId.isEmpty()) {
            return wineService.getAllWineRatings();
        } else if (wineId.isPresent()) {
            return wineService.getWineRatingByWineId(wineId.get());
        } else {
            return wineService.getWineRatingsByUser(user.get());
        }
    }

    @PutMapping("/api/v1/wineRating")
    public void addWineRating(@RequestBody WineRatingRequest wineRatingRequest) {
        
    }
}
