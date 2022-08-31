package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineFriendsRequest;
import com.lordgasmic.collections.wine.models.WineRatingEditRequest;
import com.lordgasmic.collections.wine.models.WineRatingRequest;
import com.lordgasmic.collections.wine.service.WineRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private WineRatingService service;

    @GetMapping("/api/v1/wineRating")
    public Object getWineRating(@RequestParam("user") final Optional<String> user,
                                @RequestParam("wineId") final Optional<Integer> wineId) throws SQLException {
        if (user.isEmpty() && wineId.isEmpty()) {
            return service.getAllWineRatings();
        } else if (wineId.isPresent() && user.isPresent()) {
            return service.getWineRatingsByWineIdByUser(wineId.get(), user.get());
        } else if (wineId.isPresent()) {
            return service.getWineRatingByWineId(wineId.get());
        } else {
            return service.getWineRatingsByUser(user.get());
        }
    }

    @PutMapping("/api/v1/wineRating")
    public Object addWineRating(@RequestBody final WineRatingRequest wineRatingRequest) throws SQLException {
        return service.addWineRating(wineRatingRequest);
    }

    @PostMapping("/api/v1/wineRating")
    public Object getWineRatingByUsersByWineIds(@RequestBody final WineFriendsRequest request) throws SQLException {
        return service.getWineRatingsByUsersByWineIds(request);
    }

    @PatchMapping("/api/v1/wineRating")
    public Object editWineRating(@RequestBody final WineRatingEditRequest request) throws SQLException {
        return service.editWineRating(request);
    }
}
