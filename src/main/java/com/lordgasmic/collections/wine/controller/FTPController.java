package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.service.FTPUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@Slf4j
public class FTPController {

    @Autowired
    private FTPUpload ftpUpload;

    @PutMapping("/api/v1/ftp")
    public Object addWineImage(@RequestParam("imageFile") final MultipartFile imageFile) throws IOException, SQLException {
        ftpUpload.doUpload(imageFile);
        return "{'derp':'success'}";
    }
}
