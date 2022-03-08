package com.lordgasmic.collections.wine.models;

import lombok.Data;

@Data
public class WineRatingResponse {
    private int id;
    private int wineId;
    private String user;
    private String date;
    private String rating;
}
