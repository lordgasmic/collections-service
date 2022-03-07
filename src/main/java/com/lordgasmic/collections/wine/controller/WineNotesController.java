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
import java.util.Optional;

@RestController
@Slf4j
public class WineNotesController {

    @Autowired
    private WineNotesService service;

    @GetMapping("/api/v1/wineNotes")
    public Object getWineNotes(@RequestParam("user") final Optional<String> user,
                               @RequestParam("wineId") final Optional<Integer> wineId) throws SQLException {
        if (user.isEmpty() && wineId.isEmpty()) {
            return service.getAllWineNotes();
        } else if (wineId.isPresent()) {
            return service.getWineNotesByWineId(wineId.get());
        } else {
            return service.getWineNotesByUser(user.get());
        }
    }

    @PutMapping("/api/v1/wineNotes")
    public void addWineNote(@RequestBody final WineNoteRequest wineNoteRequest) {

    }
}
