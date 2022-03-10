package com.lordgasmic.collections.wine.models;

import lombok.Data;

@Data
public class WineImage {
    private int id;
    private int wineId;
    private String label;
    private byte[] image;
    private String mimeType;
}
