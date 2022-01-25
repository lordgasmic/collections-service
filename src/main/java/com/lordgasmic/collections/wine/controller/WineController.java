package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineryResponse;
import com.lordgasmic.collections.wine.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping("/api/v1/wineries")
    public List<WineryResponse> getWineries() throws SQLException {
        return wineService.getWineries();
    }
}
