package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class WineImageController {

    @PutMapping("/api/v1/wineImages")
    public Object addWineImage(@RequestParam("imageFile") final MultipartFile imageFile) throws IOException {
        log.info("Image Upload:");
        log.info("Filename: " + imageFile.getOriginalFilename());
        log.info("Filename: " + imageFile.getName());
        log.info("File Bytes: " + imageFile.getBytes().length);
        log.info("File size: " + imageFile.getSize());
        log.info("Content-type: " + imageFile.getContentType());

        return new WineResponse();
    }
}
