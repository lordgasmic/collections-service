package com.lordgasmic.collections.wine.models;

import lombok.Data;

@Data
public class WineRequest {
    private int wineryId;
    private String name;
    private String style;
}
