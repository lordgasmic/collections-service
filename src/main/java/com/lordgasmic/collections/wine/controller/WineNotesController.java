package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineNoteRequest;
import com.lordgasmic.collections.wine.service.WineNotesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Slf4j
public class WineNotesController {

    @Autowired
    private WineNotesService service;

    @GetMapping("/api/v1/wineNotes")
    public Object getWineNotes(@RequestParam("user") final String user, @RequestParam("wineId") final Integer wineId) throws SQLException {
        if (user == null && wineId == null) {
            return service.getAllWineNotes();
        } else if (user != null && wineId != null) {
            return service.getWineNotesByWineIdByUser(wineId, user);
        } else if (wineId != null) {
            return service.getWineNotesByWineId(wineId);
        } else {
            return service.getWineNotesByUser(user);
        }
    }

    @PutMapping("/api/v1/wineNotes")
    public Object addWineNotes(@RequestBody final WineNoteRequest wineNoteRequest) throws SQLException {
        return service.addWineNotes(wineNoteRequest);
    }
}
