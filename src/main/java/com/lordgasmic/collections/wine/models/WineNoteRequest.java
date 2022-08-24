package com.lordgasmic.collections.wine.models;

import lombok.Data;

import java.util.List;

@Data
public class WineNoteRequest {
    private int wineId;
    private String user;
    private String date;
    List<String> wineNotes;
    List<WineNoteUpsert> upsert;
}
