package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineryRequest;
import com.lordgasmic.collections.wine.models.WineryResponse;
import com.lordgasmic.collections.wine.service.WineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
public class WineryController {

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

    @PutMapping("/api/v1/wineries")
    public void addWinery(@RequestBody final WineryRequest wineryRequest) {
        
    }
}
