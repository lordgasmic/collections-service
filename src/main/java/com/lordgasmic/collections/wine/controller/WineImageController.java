package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineImageResponse;
import com.lordgasmic.collections.wine.service.WineImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@Slf4j
public class WineImageController {

    @Autowired
    private WineImageService service;

    @PutMapping("/api/v1/wineImages")
    public WineImageResponse addWineImage(@RequestParam("wineId") final int wineId,
                                          @RequestParam("label") final String label,
                                          @RequestParam("imageFile") final MultipartFile imageFile) throws IOException, SQLException {
        return service.addWineImage(wineId, label, imageFile);
    }

    @GetMapping("/api/v1/wineImages")
    public WineImageResponse getWineImages(@RequestParam("wineId") final int wineId) throws SQLException {
        return service.getWineImages(wineId);
    }
}
